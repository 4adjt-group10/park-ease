package com.parkease.parkease.domain;

import com.parkease.admin.apllication.dto.PriceDTO;
import com.parkease.admin.domain.PriceService;
import com.parkease.parkease.application.ParkeTypeEnum;
import com.parkease.parkease.application.ParkingLotFormDTO;
import com.parkease.parkease.infrastructure.ParkingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ParkingService {
    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    PriceService priceService;

    public ParkingLot parkingLotIn(ParkingLotFormDTO parkingLotFormDTO) {
        if(parkingLotFormDTO.parkeTypeEnum().equals(ParkeTypeEnum.FIXEDTIME)){
            return parkingLotInFixedTime (parkingLotFormDTO);
        }else {
            return parkingLotInVariableTime(parkingLotFormDTO);
        }
    }

    private ParkingLot parkingLotInVariableTime(ParkingLotFormDTO parkingLotFormDTO) {
        return parkingRepository.save(new ParkingLot(parkingLotFormDTO, LocalDateTime.now(),
                null, new BigDecimal(0)));

    }

    private ParkingLot parkingLotInFixedTime(ParkingLotFormDTO parkingLotFormDTO) {
        if(Objects.isNull(parkingLotFormDTO.timeParking())|| parkingLotFormDTO.timeParking() < 1){

            throw new EntityNotFoundException("Time is empty.");
        }
        LocalDateTime endAt = LocalDateTime.now().plusHours(parkingLotFormDTO.timeParking());

        if(priceService.findAll().stream().findFirst().isEmpty()){
            throw new EntityNotFoundException("The price doesn't exist.");
        }else {
            PriceDTO priceValue = priceService.findAll().stream().findFirst().get();
            BigDecimal finalPrice = priceValue.
                    price().
                    multiply(BigDecimal.
                            valueOf(parkingLotFormDTO.
                                    timeParking()));

            return parkingRepository.save(new ParkingLot(parkingLotFormDTO, LocalDateTime.now(),
                    endAt, finalPrice));

        }
    }
}
