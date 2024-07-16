package com.parkease.paymentVoucher.domain;

import com.parkease.parkingmeter.domain.ParkingMeter;
import com.parkease.payment.domain.Payment;
import com.parkease.paymentVoucher.infrastructure.PaymentVoucherRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        ArrayList<Payment> listPayment = new ArrayList<>();

        listPayment.sort(new Comparator<Payment>() {
            @Override
            public int compare(Payment o1, Payment o2) {
                return o1.getCreationDate().compareTo(o2.getCreationDate());
            }

        });
        return paymentVoucherRepository.save(new Voucher(listPayment, parkingMeter, extraCurrentPrice));
    }


}
