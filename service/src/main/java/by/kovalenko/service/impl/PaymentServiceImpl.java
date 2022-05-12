package by.kovalenko.service.impl;

import by.kovalenko.dto.PaymentDto;
import by.kovalenko.dto.PaymentSearchAttributes;
import by.kovalenko.entity.PaymentEntity;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.mapper.PaymentMapper;
import by.kovalenko.repositories.PaymentRepository;
import by.kovalenko.service.PaymentService;
import by.kovalenko.service.UserService;
import by.kovalenko.spetification.PaymentSearchSpecification;
import by.kovalenko.util.PaymentTransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final UserService userService;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentSearchSpecification getPaymentSpecification(PaymentSearchAttributes paymentSearchAttributes) {
        return new PaymentSearchSpecification(paymentSearchAttributes);
    }

    @Override
    public Page<PaymentDto> findAll(PaymentSearchSpecification paymentSearchSpecification, Pageable pageable) {
        Page<PaymentEntity> paymentEntityPage = paymentRepository.findAll(paymentSearchSpecification, pageable);
        return paymentEntityPage.map(paymentMapper::paymentEntityToPaymentDto);
    }

    @Override
    public void createPaymentTransaction(double depositAmount, UUID walletId, PaymentTransactionType transactionType) {
        UserEntity userEntity = userService.findByWalletId(walletId);
        paymentRepository.save(new PaymentEntity(
                new Date(), transactionType, depositAmount, userEntity));
    }
}
