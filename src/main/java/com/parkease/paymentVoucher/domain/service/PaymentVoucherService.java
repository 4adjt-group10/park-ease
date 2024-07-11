package com.parkease.paymentVoucher.domain.service;

import com.parkease.parkingmeter.domain.ParkingMeter;
import com.parkease.payment.domain.Payment;
import com.parkease.paymentVoucher.infrastructure.repository.PaymentVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentVoucherService {
    @Autowired
    PaymentVoucherRepository paymentVoucherRepository;


    public Voucher createdVoucherVariable(Payment payment, ParkingMeter parkingMeter) {
       return paymentVoucherRepository.save(new Voucher(payment, parkingMeter));

    }

    public Voucher createdVoucherFixedTime(List<Payment> payments, ParkingMeter parkingMeter) {
        return paymentVoucherRepository.save(new Voucher(payments, parkingMeter));
    }
}
