package dev.boot.service;

import dev.boot.domain.Payments;
import dev.boot.dto.PaymentDTO;
import dev.boot.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final ModelMapper modelMapper;
    private final AccountService accountService;

    public PaymentDTO save(long ItemId , PaymentDTO paymentDTO) {
        accountService.update(ItemId,paymentDTO.getAmount());
        paymentDTO.setItemId(ItemId);
        return modelMapper.map(repository.save(modelMapper.map(paymentDTO, Payments.class)),PaymentDTO.class);
    }

}
