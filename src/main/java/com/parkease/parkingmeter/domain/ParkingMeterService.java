package com.parkease.parkingmeter.domain;

import com.parkease.admin.apllication.dto.PriceDTO;
import com.parkease.admin.domain.PriceService;
import com.parkease.driver.domain.Driver;
import com.parkease.driver.domain.DriverService;
import com.parkease.parkingmeter.application.ParkingMeterFormDTO;
import com.parkease.parkingmeter.infrastructure.ParkingMeterRepository;
import com.parkease.payment.domain.Payment;
import com.parkease.payment.domain.PaymentMethod;
import com.parkease.payment.domain.PaymentService;
import com.parkease.payment.domain.PaymentStatus;
import com.parkease.vehicle.domain.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public ParkingMeterService(ParkingMeterRepository parkingMeterRepository,
                               PriceService priceService,
                               DriverService driverService,
                               VehicleService vehicleService, PaymentService paymentService) {
        this.parkingMeterRepository = parkingMeterRepository;
        this.priceService = priceService;
        this.driverService = driverService;
        this.vehicleService = vehicleService;
        this.paymentService = paymentService;
    }

    public ParkingMeter parkingLotIn(ParkingMeterFormDTO formDTO) {
        return formDTO.isFixedTime()
                ? parkingLotInFixedTime(formDTO)
                : parkingLotInVariableTime(formDTO);
    }

    private ParkingMeter parkingLotInVariableTime(ParkingMeterFormDTO formDTO) {
        if(formDTO.paymentMethod().equals(PIX)) {
            throw new IllegalArgumentException("PIX payment method is not allowed for variable time parking");
        }
        return parkingMeterRepository.save(new ParkingMeter(formDTO, now(),
                null, new BigDecimal(0)));

    }

    //TODO: Implementar o fluxo de cobrança para adicionar no caso de pagamento fixo
    private ParkingMeter parkingLotInFixedTime(ParkingMeterFormDTO formDTO) {

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

            return parkingMeterRepository.save(new ParkingMeter(formDTO, now(),
                    endAt, finalPrice));

        }
    }

    //TODO: Implementar o fluxo de cobrança para adicionar no caso de pagamento fixo com tempo extra
    public void parkingLotOut(String parkingMeterId) {
        ParkingMeter parkingMeter = parkingMeterRepository.findById(parkingMeterId)
                .orElseThrow(EntityNotFoundException::new);
        Driver driver = driverService.findById(parkingMeter.getDriverId());

        if(parkingMeter.isFixedTime()) {
            Payment lastPayment = paymentService.findLastPaymentByDriver(driver.getId());

            if(parkingMeter.getEndAt().isBefore(now())) {

            }
        }

        Payment payment = new Payment(driver, parkingMeter.getPrice(), parkingMeter.getPaymentMethod(), PAID);

        parkingMeterRepository.delete(parkingMeter);
    }
}
