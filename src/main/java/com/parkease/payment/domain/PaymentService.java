package com.parkease.payment.domain;

import com.parkease.driver.domain.Driver;
import com.parkease.driver.domain.DriverService;
import com.parkease.payment.application.PaymentDTO;
import com.parkease.payment.application.PaymentFormDTO;
import com.parkease.payment.infrastructure.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final DriverService driverService;

    public PaymentService(PaymentRepository paymentRepository, DriverService driverService) {
        this.paymentRepository = paymentRepository;
        this.driverService = driverService;
    }

    public PaymentDTO createPayment(PaymentFormDTO formDTO) {
        Driver driver = driverService.findById(formDTO.driverId());
        Payment payment = paymentRepository.save(new Payment(driver, formDTO));
        return new PaymentDTO(payment);
    }

    public PaymentDTO getPayment(String id) {
        return paymentRepository.findById(id).map(PaymentDTO::new).orElseThrow(EntityNotFoundException::new);
    }

    public List<PaymentDTO> listPayments() {
        return paymentRepository.findAll().stream().map(PaymentDTO::new).toList();
    }

    public PaymentDTO findLastByDriver(String id) {
        return paymentRepository.findFirstByDriverIdOrderByIdDesc(id).map(PaymentDTO::new).orElseThrow(EntityNotFoundException::new);
    }

}
