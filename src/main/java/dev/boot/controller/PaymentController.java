package dev.boot.controller;


import dev.boot.dto.PaymentDTO;
import dev.boot.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PutMapping ("/pay/{itemId}")
    public ResponseEntity<PaymentDTO> addPayment(@PathVariable long itemId, @RequestBody PaymentDTO paymentDTO)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.save(itemId,paymentDTO));
    }
}
