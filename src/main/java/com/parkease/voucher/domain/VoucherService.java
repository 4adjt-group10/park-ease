package com.parkease.voucher.domain;

import com.parkease.parkingmeter.domain.ParkingMeter;
import com.parkease.payment.domain.Payment;
import com.parkease.voucher.application.VoucherDTO;
import com.parkease.voucher.infrastructure.VoucherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoucherService {

    private final VoucherRepository voucherRepository;

    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public Voucher createdVoucherVariable(Payment payment, ParkingMeter parkingMeter) {
       return voucherRepository.save(new Voucher(payment, parkingMeter));
    }

    public Voucher createdVoucherFixedTime(List<Payment> payments,
                                           ParkingMeter parkingMeter,
                                           BigDecimal extraCurrentPrice,
                                           LocalDateTime extraLeft) {

        return voucherRepository.save(new Voucher(payments, parkingMeter, extraCurrentPrice, extraLeft));
    }

    public Page<VoucherDTO> listAllVouchers(Pageable pageable) {
        return voucherRepository.findAll(pageable).map(VoucherDTO::new);
    }

    public Page<VoucherDTO> listAllVouchersByDriverId(Pageable pageable, String driverId) {
        return voucherRepository.findAllByDriverId(pageable, driverId).map(VoucherDTO::new);
    }

    public Page<VoucherDTO> listAllVouchersByArrivedAtBetween(Pageable pageable, LocalDateTime start, LocalDateTime end) {
        return voucherRepository.findAllByArrivedAtBetween(pageable, start, end).map(VoucherDTO::new);
    }
}
