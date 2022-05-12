package by.kovalenko.service;

import by.kovalenko.dto.PaymentDto;
import by.kovalenko.dto.PaymentSearchAttributes;
import by.kovalenko.spetification.PaymentSearchSpecification;
import by.kovalenko.util.PaymentTransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PaymentService {
    PaymentSearchSpecification getPaymentSpecification(PaymentSearchAttributes paymentSearchAttributes);

    Page<PaymentDto> findAll(PaymentSearchSpecification paymentSearchSpecification, Pageable pageable);

    void createPaymentTransaction(double depositAmount, UUID walletId, PaymentTransactionType replenishment);
}
