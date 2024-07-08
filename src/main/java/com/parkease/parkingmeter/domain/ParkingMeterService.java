package com.parkease.parkingmeter.domain;

import com.parkease.admin.apllication.dto.PriceDTO;
import com.parkease.admin.domain.PriceService;
import com.parkease.driver.domain.DriverService;
import com.parkease.parkingmeter.application.ParkingMeterType;
import com.parkease.parkingmeter.application.ParkingLotFormDTO;
import com.parkease.parkingmeter.infrastructure.ParkingMeterRepository;
import com.parkease.vehicle.domain.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.parkease.parkingmeter.application.ParkingMeterType.FIXED_TIME;
import static java.math.BigDecimal.valueOf;

@Service
public class ParkingMeterService {

    private final ParkingMeterRepository parkingMeterRepository;
    private final PriceService priceService;
    private final DriverService driverService;
    private final VehicleService vehicleService;

    public ParkingMeterService(ParkingMeterRepository parkingMeterRepository,
                               PriceService priceService,
                               DriverService driverService,
                               VehicleService vehicleService) {
        this.parkingMeterRepository = parkingMeterRepository;
        this.priceService = priceService;
        this.driverService = driverService;
        this.vehicleService = vehicleService;
    }

    public ParkingMeter parkingLotIn(ParkingLotFormDTO formDTO) {
        return formDTO.isFixedTime()
                ? parkingLotInFixedTime(formDTO)
                : parkingLotInVariableTime(formDTO);
    }

    private ParkingMeter parkingLotInVariableTime(ParkingLotFormDTO parkingLotFormDTO) {
        return parkingMeterRepository.save(new ParkingMeter(parkingLotFormDTO, LocalDateTime.now(),
                null, new BigDecimal(0)));

    }

    private ParkingMeter parkingLotInFixedTime(ParkingLotFormDTO formDTO) {

        if(!formDTO.hasValidTimeParking()) {
            throw new IllegalArgumentException("Invalid time parking");
        }

        if(!driverService.existsById(formDTO.driverId()) || !vehicleService.existsById(formDTO.vehicleId())){
            throw new EntityNotFoundException("Driver or vehicle not found");
        }

        Long fixedTime = formDTO.timeParking().orElseThrow(IllegalArgumentException::new);
        LocalDateTime endAt = LocalDateTime.now().plusHours(fixedTime);

        if(priceService.findAll().stream().findFirst().isEmpty()){
            throw new EntityNotFoundException("The price doesn't exist.");
        }else {
            PriceDTO priceValue = priceService.findAll().stream().findFirst().get();
            BigDecimal finalPrice = priceValue.price().multiply(valueOf(fixedTime));

            return parkingMeterRepository.save(new ParkingMeter(formDTO, LocalDateTime.now(),
                    endAt, finalPrice));

        }
    }
}
