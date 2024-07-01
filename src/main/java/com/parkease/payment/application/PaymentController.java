package com.parkease.payment.application;

import com.parkease.payment.domain.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<PaymentDTO>> listPayments() {
        return ResponseEntity.ok(paymentService.listPayments());
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody @Valid PaymentFormDTO formDTO) {
        return ResponseEntity.ok(paymentService.createPayment(formDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable String id) {
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<PaymentDTO> listPaymentsByDriver(@PathVariable String id) {
        return ResponseEntity.ok(paymentService.findLastByDriver(id));
    }
}
