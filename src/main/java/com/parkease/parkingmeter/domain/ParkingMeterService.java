package com.parkease.parkingmeter.domain;

import com.parkease.admin.apllication.dto.PriceDTO;
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
import com.parkease.paymentVoucher.domain.service.PaymentVoucherService;
import com.parkease.vehicle.domain.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.parkease.payment.domain.PaymentMethod.PIX;
import static com.parkease.payment.domain.PaymentStatus.PAID;
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
    private final PaymentVoucherService paymentVoucherService;

    public ParkingMeterService(ParkingMeterRepository parkingMeterRepository, PriceService priceService, DriverService driverService, VehicleService vehicleService, PaymentService paymentService, InvoiceService invoiceService, PaymentVoucherService paymentVoucherService) {
        this.parkingMeterRepository = parkingMeterRepository;
        this.priceService = priceService;
        this.driverService = driverService;
        this.vehicleService = vehicleService;
        this.paymentService = paymentService;
        this.invoiceService = invoiceService;
        this.paymentVoucherService = paymentVoucherService;
    }

    public ParkingMeter arrivingParkingLot(ParkingMeterFormDTO formDTO) {
        return formDTO.hasFixedTime()
                ? parkingInFixedTime(formDTO)
                : parkingInVariableTime(formDTO);
    }

    private ParkingMeter parkingInVariableTime(ParkingMeterFormDTO formDTO) {
        if(formDTO.paymentMethod().equals(PIX)) {
            throw new IllegalArgumentException("PIX payment method is not allowed for variable time parking");
        }
        return parkingMeterRepository.save(new ParkingMeter(formDTO, now(),
                null, new BigDecimal(0)));

    }

    private ParkingMeter parkingInFixedTime(ParkingMeterFormDTO formDTO) {

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
            PriceDTO priceValue = priceService.findAll().stream().findFirst().get();
            BigDecimal finalPrice = priceValue.price().multiply(valueOf(fixedTime));
            Payment payment = paymentService.createNewPayment(new PaymentFormDTO(formDTO.driverId(), finalPrice, formDTO.paymentMethod(), PENDING));
            invoiceService.createInvoice(payment, now());
            return parkingMeterRepository.save(new ParkingMeter(formDTO, now(),
                    endAt, finalPrice));
        }
    }

    public void leavingVariableTime(String parkingMeterId) {
        ParkingMeter parkingMeter = parkingMeterRepository.findById(parkingMeterId)
                .orElseThrow(EntityNotFoundException::new);
        Driver driver = driverService.findById(parkingMeter.getDriverId());
        PriceDTO priceValue = priceService.findAll().stream().findFirst().get();
        BigDecimal finalPrice = priceValue.price().multiply(valueOf(parkingMeter.getTotalHours(parkingMeter.getStartAt(),now())));
        Payment payment = paymentService
                .savePayment(new Payment(driver, finalPrice, parkingMeter.getPaymentMethod(), PENDING));
        invoiceService.createInvoice(payment, now());
        paymentVoucherService.createdVoucherVariable(payment, parkingMeter);
        deleteAll(parkingMeter);

    }

    private void deleteAll(ParkingMeter parkingMeter) {
        parkingMeterRepository.delete(parkingMeter);
        invoiceService.deleteAll(parkingMeter);

    }

    public void leavingFixedTime(String parkingMeterId, Optional<PaymentMethod> paymentMethod) {
        ParkingMeter parkingMeter = parkingMeterRepository.findById(parkingMeterId)
                .orElseThrow(EntityNotFoundException::new);
        Driver driver = driverService.findById(parkingMeter.getDriverId());
        Payment lastPayment = paymentService.findLastPaymentByDriver(driver.getId());

        if(parkingMeter.getEndAt().isBefore(now())) {
            if (paymentMethod.isEmpty()) {
                throw new IllegalArgumentException("Payment method is required");
            }
            if (paymentMethod.get().equals(PIX)) {
                throw new IllegalArgumentException("PIX payment method is not allowed");
            }
            PriceDTO priceValue = priceService.findAll().stream().findFirst().get();
            BigDecimal finalPrice = priceValue.price()
                    .multiply(valueOf(parkingMeter.getTotalHours(parkingMeter.getEndAt(), now())));
            Payment payment = paymentService
                    .savePayment(new Payment(driver, finalPrice, parkingMeter.getPaymentMethod(), PENDING));
            invoiceService.createInvoice(payment, now());
        }
        List<Payment> payments = paymentService.findAllByDriver(parkingMeter.getDriverId());
        paymentVoucherService.createdVoucherFixedTime(payments, parkingMeter);
        deleteAll(parkingMeter);
    }


    public List<ParkingMeterDTO> listAllParkingMeters() {
        return parkingMeterRepository.findAll().stream().map(ParkingMeterDTO::new).toList();
    }
}
