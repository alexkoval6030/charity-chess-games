package by.kovalenko.service.impl;

import by.kovalenko.entity.PaymentEntity;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.repositories.PaymentRepository;
import by.kovalenko.service.PaymentService;
import by.kovalenko.service.UserService;
import by.kovalenko.util.PaymentTransactionType;
import lombok.RequiredArgsConstructor;
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

    @Override
    public void createPaymentTransaction(double depositAmount, UUID walletId, PaymentTransactionType transactionType) {
        UserEntity userEntity = userService.findByWalletId(walletId);
        paymentRepository.save(new PaymentEntity(
                new Date(), transactionType, depositAmount, userEntity));
    }
}
