package com.parkease.parkingmeter.domain;

import com.parkease.admin.apllication.PriceDTO;
import com.parkease.admin.domain.PriceService;
import com.parkease.driver.domain.Driver;
import com.parkease.driver.domain.DriverService;
import com.parkease.invoice.domain.InvoiceService;
import com.parkease.parkingmeter.application.ParkingMeterDTO;
import com.parkease.parkingmeter.application.ParkingMeterFormDTO;
import com.parkease.parkingmeter.infrastructure.ParkingMeterRepository;
import com.parkease.payment.application.PaymentFormDTO;
import com.parkease.payment.domain.Payment;
import com.parkease.payment.domain.PaymentMethod;
import com.parkease.payment.domain.PaymentService;
import com.parkease.voucher.application.VoucherDTO;
import com.parkease.voucher.domain.VoucherService;
import com.parkease.voucher.domain.Voucher;
import com.parkease.vehicle.domain.Vehicle;
import com.parkease.vehicle.domain.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static com.parkease.parkingmeter.application.ParkingMeterType.FIXED_TIME;
import static com.parkease.parkingmeter.application.ParkingMeterType.VARIABLE_TIME;
import static com.parkease.payment.domain.PaymentMethod.PIX;
import static com.parkease.payment.domain.PaymentStatus.PENDING;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDateTime.now;

@Service
public class ParkingMeterService {

    private final ParkingMeterRepository parkingMeterRepository;
    private final PriceService priceService;
    private final DriverService driverService;
    private final VehicleService vehicleService;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;
    private final VoucherService voucherService;
    private final Logger logger = Logger.getLogger(ParkingMeterService.class.getName());


    public ParkingMeterService(ParkingMeterRepository parkingMeterRepository,
                               PriceService priceService,
                               DriverService driverService,
                               VehicleService vehicleService,
                               PaymentService paymentService,
                               InvoiceService invoiceService,
                               VoucherService voucherService) {
        this.parkingMeterRepository = parkingMeterRepository;
        this.priceService = priceService;
        this.driverService = driverService;
        this.vehicleService = vehicleService;
        this.paymentService = paymentService;
        this.invoiceService = invoiceService;
        this.voucherService = voucherService;
    }

    public ParkingMeterDTO arrivingParkingLot(ParkingMeterFormDTO formDTO) {
        return formDTO.hasFixedTime()
                ? parkingInFixedTime(formDTO)
                : parkingInVariableTime(formDTO);
    }

    private ParkingMeterDTO parkingInVariableTime(ParkingMeterFormDTO formDTO) {
        if(formDTO.paymentMethod().equals(PIX)) {
            throw new IllegalArgumentException("PIX payment method is not allowed for variable time parking");
        }
        BigDecimal price = priceService.findCurrentPrice().value();
        ParkingMeter parkingMeter = parkingMeterRepository.save(new ParkingMeter(formDTO, now(), null, price));
        return new ParkingMeterDTO(parkingMeter);
    }

    private ParkingMeterDTO parkingInFixedTime(ParkingMeterFormDTO formDTO) {

        if(!formDTO.hasValidTimeParking()) {
            throw new IllegalArgumentException("Invalid time parking");
        }

        if(!driverService.existsById(formDTO.driverId()) || !vehicleService.existsById(formDTO.vehicleId())){
            throw new EntityNotFoundException("Driver or vehicle not found");
        }

        Long fixedTime = formDTO.timeParking().orElseThrow(IllegalArgumentException::new);
        LocalDateTime endAt = now().plusHours(fixedTime);

        if(priceService.findAll().stream().findFirst().isEmpty()){
            throw new EntityNotFoundException("The price doesn't exist.");
        }else {
            PriceDTO priceValue = priceService.findCurrentPrice();
            BigDecimal finalPrice = priceValue.value().multiply(valueOf(fixedTime));
            Payment payment = paymentService.createNewPayment(new PaymentFormDTO(formDTO.driverId(), finalPrice, formDTO.paymentMethod(), PENDING));
            invoiceService.createInvoice(payment, now());
            ParkingMeter parkingMeter = parkingMeterRepository.save(new ParkingMeter(formDTO, now(), endAt, priceValue.value()));
            return new ParkingMeterDTO(parkingMeter);
        }
    }

    public VoucherDTO leavingVariableTime(String parkingMeterId, Optional<PaymentMethod> paymentMethod) {
        ParkingMeter parkingMeter = parkingMeterRepository.findById(parkingMeterId)
                .orElseThrow(EntityNotFoundException::new);
        Driver driver = driverService.findById(parkingMeter.getDriverId());
        Vehicle vehicle = vehicleService.findById(parkingMeter.getVehicleId());
        BigDecimal finalPrice = parkingMeter.getPrice()
                .multiply(valueOf(parkingMeter.getTotalHours(parkingMeter.getStartAt(), now())));
        paymentMethod.filter(PIX::equals).ifPresent(method -> {
            throw new IllegalArgumentException("PIX payment method is not allowed");
        });
        PaymentMethod newPaymentMethod = paymentMethod.orElse(parkingMeter.getPaymentMethod());
        Payment payment = paymentService
                .savePayment(new Payment(driver, finalPrice, newPaymentMethod, PENDING));
        invoiceService.createInvoice(payment, now());
        Voucher voucher = voucherService.createdVoucherVariable(payment, parkingMeter);
        deleteAll(parkingMeter);
        return new VoucherDTO(voucher, driver.getFullName(), vehicle.getLicensePlate());
    }

    public VoucherDTO leavingFixedTime(String parkingMeterId, Optional<PaymentMethod> paymentMethod) {
        ParkingMeter parkingMeter = parkingMeterRepository.findById(parkingMeterId)
                .orElseThrow(EntityNotFoundException::new);
        Driver driver = driverService.findById(parkingMeter.getDriverId());
        Vehicle vehicle = vehicleService.findById(parkingMeter.getVehicleId());
        LocalDateTime now = now();
        if(parkingMeter.getEndAt().isBefore(now)) {
            if (paymentMethod.isEmpty()) {
                throw new IllegalArgumentException("Payment method is required");
            }
            if (paymentMethod.get().equals(PIX)) {
                throw new IllegalArgumentException("PIX payment method is not allowed");
            }
            BigDecimal finalPrice = getFixedTimeFinalPrice(parkingMeter, now);
            Payment payment = paymentService
                    .savePayment(new Payment(driver, finalPrice, paymentMethod.get(), PENDING));
            invoiceService.createInvoice(payment, now);
            List<Payment> payments = paymentService.findAllByDriverId(parkingMeter.getDriverId());
            BigDecimal extraCurrentPrice = payments.get(payments.size() - 1).getAmount()
                    .divide(valueOf(parkingMeter.getTotalHours(parkingMeter.getEndAt(), now)));
            Voucher voucher = voucherService.createdVoucherFixedTime(payments, parkingMeter, extraCurrentPrice, now);
            deleteAll(parkingMeter);
            return new VoucherDTO(voucher, driver.getFullName(), vehicle.getLicensePlate());
        }else{
            var payment = paymentService.findLastPaymentByDriver(parkingMeter.getDriverId());
            Voucher voucher = voucherService.createdVoucherVariable(payment, parkingMeter);
            deleteAll(parkingMeter);
            return new VoucherDTO(voucher, driver.getFullName(), vehicle.getLicensePlate());
        }
    }

    private BigDecimal getFixedTimeFinalPrice(ParkingMeter parkingMeter, LocalDateTime now) {
        PriceDTO priceValue  = priceService.findCurrentPrice();
        return priceValue.value()
                .multiply(valueOf(parkingMeter.getTotalHours(parkingMeter.getEndAt(), now)));
    }

    public BigDecimal getVariableTimeFinalPrice(ParkingMeter parkingMeter) {
        return parkingMeter.getPrice()
                .multiply(valueOf(parkingMeter.getTotalHours(parkingMeter.getStartAt(), now())));
    }

    private void deleteAll(ParkingMeter parkingMeter) {
        parkingMeterRepository.delete(parkingMeter);
        invoiceService.deleteAllInvoicesAndPayments(parkingMeter);
    }

    public List<ParkingMeterDTO> listAllParkingMeters() {
        return parkingMeterRepository.findAll().stream().map(ParkingMeterDTO::new).toList();
    }

    public ParkingMeterDTO downTest(String id, long hours) {
        ParkingMeter parkingMeter = parkingMeterRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        parkingMeter.downHours(hours);
        return new ParkingMeterDTO(parkingMeterRepository.save(parkingMeter));
    }

    /**
     * This method is scheduled to run every 5 minutes, every day.
     * {@code @Scheduled(cron = "0 0/5 * * * *")} configures this scheduling.
     */
    @Scheduled(cron = "0 0/5 * * * *")
    @Async
    public void fixedTimeAlert() {
        LocalDateTime now = now();
        parkingMeterRepository.findAllByTypeAndEndAtAfter(FIXED_TIME, now)
                .stream()
                .filter(parkingMeter -> {
                    long minutesToEnd = ChronoUnit.MINUTES.between(now, parkingMeter.getEndAt());
                    return minutesToEnd <= 10;
                }).forEach(parkingMeter -> {
                    logger.warning("ParkingMeter " + parkingMeter.getId() + " is late");
                    logger.info("Sending alert message to external service");
                });
        parkingMeterRepository.findAllByTypeAndEndAtBefore(FIXED_TIME, now)
                .stream()
                .filter(parkingMeter -> {
                    long minutesSinceStart = ChronoUnit.MINUTES.between(parkingMeter.getEndAt(), now);
                    long minutesUntilNextHour = 60 - (minutesSinceStart % 60);
                    return minutesUntilNextHour <= 10;
                }).forEach(parkingMeter -> {
                    logger.warning("ParkingMeter " + parkingMeter.getId() + " will add 1 hour");
                    logger.info("Sending alert message to external service");
                });
        logger.info("Fixed time alerts executed");
    }

    /**
     * This method is scheduled to run every 5 minutes, every day.
     * {@code @Scheduled(cron = "0 0/5 * * * *")} configures this scheduling.
     */
    @Scheduled(cron = "0 0/5 * * * *")
    @Async
    public void variableTimeAlert() {
        LocalDateTime now = now();
        parkingMeterRepository.findAllByType(VARIABLE_TIME)
                .stream()
                .filter(parkingMeter -> {
                    long minutesSinceStart = ChronoUnit.MINUTES.between(parkingMeter.getStartAt(), now);
                    long minutesUntilNextHour = 60 - (minutesSinceStart % 60);
                    return minutesUntilNextHour <= 10;
                }).forEach(parkingMeter -> {
                    logger.warning("ParkingMeter " + parkingMeter.getId() + " will add 1 hour");
                    logger.info("Sending alert message to external service");
                });
        logger.info("Variable time alert executed");
    }

    public BigDecimal getFinalPrice(String id) {
        ParkingMeter parkingMeter = parkingMeterRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return parkingMeter.isFixedTime()
                ? getFixedTimeFinalPrice(parkingMeter, now())
                : getVariableTimeFinalPrice(parkingMeter);
    }
}

