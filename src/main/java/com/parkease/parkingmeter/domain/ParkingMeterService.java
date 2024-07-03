package com.parkease.parkingmeter.domain;

import com.parkease.driver.domain.Driver;
import com.parkease.driver.domain.DriverService;
import com.parkease.parkingmeter.application.ParkingMeterDTO;
import com.parkease.parkingmeter.application.ParkingMeterFormDTO;
import com.parkease.parkingmeter.infrastructure.ParkingMeterRepository;
import com.parkease.vehicle.domain.Vehicle;
import com.parkease.vehicle.domain.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingMeterService {

    private final ParkingMeterRepository parkingMeterRepository;
    private final ParkingZoneService parkingZoneService;
    private final DriverService driverService;
    private final VehicleService vehicleService;

    public ParkingMeterService(ParkingMeterRepository parkingMeterRepository,
                               ParkingZoneService parkingZoneService,
                               DriverService driverService,
                               VehicleService vehicleService) {
        this.parkingMeterRepository = parkingMeterRepository;
        this.parkingZoneService = parkingZoneService;
        this.driverService = driverService;
        this.vehicleService = vehicleService;
    }

    @Transactional
    public ParkingMeterDTO createParkingMeter(ParkingMeterFormDTO formDTO) {
        ParkingSpace parkingSpace = parkingZoneService.getParkingSpace(formDTO.parkingSpaceId());
        Driver driver = driverService.findById(formDTO.driverId());
        Vehicle vehicle = vehicleService.findById(formDTO.vehicleId());
        ParkingMeter parkingMeter = parkingMeterRepository.save(new ParkingMeter(parkingSpace, formDTO, driver, vehicle));
        return new ParkingMeterDTO(parkingMeter);
    }

    public ParkingMeterDTO getById(String id) {
        return parkingMeterRepository.findById(id).map(ParkingMeterDTO::new).orElseThrow(EntityNotFoundException::new);
    }

    public List<ParkingMeterDTO> listParkingMeters() {
        return parkingMeterRepository.findAll().stream().map(ParkingMeterDTO::new).toList();
    }

    public void deleteParkingMeter(String id) {
        parkingMeterRepository.deleteById(id);
    }
}
