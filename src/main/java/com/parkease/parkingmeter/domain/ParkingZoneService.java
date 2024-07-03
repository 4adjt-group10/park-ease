package com.parkease.parkingmeter.domain;

import com.parkease.parkingmeter.application.ParkingSpaceDTO;
import com.parkease.parkingmeter.application.ParkingSpaceFormDTO;
import com.parkease.parkingmeter.application.ParkingZoneDTO;
import com.parkease.parkingmeter.application.ParkingZoneFormDTO;
import com.parkease.parkingmeter.infrastructure.ParkingSpaceRepository;
import com.parkease.parkingmeter.infrastructure.ParkingZoneRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingZoneService {

    private final ParkingZoneRepository parkingZoneRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;

    public ParkingZoneService(ParkingZoneRepository parkingZoneRepository, ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingZoneRepository = parkingZoneRepository;
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    @Transactional
    public ParkingZoneDTO createParkingZone(ParkingZoneFormDTO formDTO) {
        ParkingZone parkingZone = parkingZoneRepository.save(new ParkingZone(formDTO));
        return new ParkingZoneDTO(parkingZone);
    }

    public ParkingZone getParkingZone(String id) {
        return parkingZoneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public ParkingZoneDTO getParkingZoneDTO(String id) {
        return new ParkingZoneDTO(getParkingZone(id));
    }

    public List<ParkingZoneDTO> getAllParkingZones() {
        return parkingZoneRepository.findAll().stream().map(ParkingZoneDTO::new).toList();
    }

    public ParkingSpace getParkingSpace(String id) {
        return parkingSpaceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<ParkingSpaceDTO> getAllParkingSpaces() {
        return parkingSpaceRepository.findAll().stream().map(ParkingSpaceDTO::new).toList();
    }

    public void addParkingSpace(String parkingZoneId, ParkingSpaceFormDTO parkingSpaceFormDTO) {
        ParkingZone parkingZone = parkingZoneRepository.findById(parkingZoneId).orElseThrow(EntityNotFoundException::new);
        ParkingSpace parkingSpace = new ParkingSpace(parkingSpaceFormDTO, parkingZoneId);
        parkingZone.addParkingSpace(parkingSpaceRepository.save(parkingSpace));
        parkingZoneRepository.save(parkingZone);
    }

    public void removeParkingSpace(String parkingZoneId, String parkingSpaceId) {
        ParkingZone parkingZone = parkingZoneRepository.findById(parkingZoneId).orElseThrow(EntityNotFoundException::new);
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(parkingSpaceId).orElseThrow(EntityNotFoundException::new);
        parkingZone.removeParkingSpace(parkingSpace);
        parkingSpaceRepository.delete(parkingSpace);
        parkingZoneRepository.save(parkingZone);
    }

    public void deleteParkingZone(String id) {
        ParkingZone parkingZone = parkingZoneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        List<ParkingSpace> parkingSpaces = parkingZone.getParkingSpaces();
        parkingSpaceRepository.deleteAll(parkingSpaces);
        parkingZoneRepository.deleteById(id);
    }
}
