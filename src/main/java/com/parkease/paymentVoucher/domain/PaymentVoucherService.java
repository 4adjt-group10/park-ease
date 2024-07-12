package com.parkease.paymentVoucher.domain;

import com.parkease.parkingmeter.domain.ParkingMeter;
import com.parkease.payment.domain.Payment;
import com.parkease.paymentVoucher.infrastructure.PaymentVoucherRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentVoucherService {

    private final PaymentVoucherRepository paymentVoucherRepository;

    public PaymentVoucherService(PaymentVoucherRepository paymentVoucherRepository) {
        this.paymentVoucherRepository = paymentVoucherRepository;
    }

    public Voucher createdVoucherVariable(Payment payment, ParkingMeter parkingMeter) {
       return paymentVoucherRepository.save(new Voucher(payment, parkingMeter));

    }

    public Voucher createdVoucherFixedTime(List<Payment> payments, ParkingMeter parkingMeter, BigDecimal extraCurrentPrice) {
        return paymentVoucherRepository.save(new Voucher(payments, parkingMeter, extraCurrentPrice));
    }
}
