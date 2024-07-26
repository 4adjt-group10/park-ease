package com.parkease.payment.domain;

import com.parkease.driver.domain.Driver;
import com.parkease.driver.domain.DriverService;
import com.parkease.payment.application.PaymentDTO;
import com.parkease.payment.application.PaymentFormDTO;
import com.parkease.payment.infrastructure.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final DriverService driverService;

    public PaymentService(PaymentRepository paymentRepository, DriverService driverService) {
        this.paymentRepository = paymentRepository;
        this.driverService = driverService;
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment createNewPayment(PaymentFormDTO formDTO) {
        Driver driver = driverService.findById(formDTO.driverId());
        return paymentRepository.save(new Payment(driver, formDTO));
    }

    public PaymentDTO createPayment(PaymentFormDTO formDTO) {
        return new PaymentDTO(createNewPayment(formDTO));
    }

    public PaymentDTO getPayment(String id) {
        return paymentRepository.findById(id).map(PaymentDTO::new).orElseThrow(EntityNotFoundException::new);
    }

    public List<PaymentDTO> listPayments() {
        return paymentRepository.findAll().stream().map(PaymentDTO::new).toList();
    }

    public PaymentDTO findLastByDriver(String id) {
        return paymentRepository.findFirstByDriver_IdOrderByCreationDateDesc(id).map(PaymentDTO::new).orElseThrow(EntityNotFoundException::new);
    }

    public Payment findLastPaymentByDriver(String id) {
        return paymentRepository.findFirstByDriver_IdOrderByCreationDateDesc(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Payment> findAllByDriverId(String driverId) {
        return paymentRepository.findAllByDriver_IdOrderByCreationDate(driverId);
    }

    public void deleteAllByDriver(String driverId) {
        paymentRepository.deleteAllByDriver_Id(driverId);
    }
}
