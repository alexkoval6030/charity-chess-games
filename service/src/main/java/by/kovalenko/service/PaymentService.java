package by.kovalenko.service;

import by.kovalenko.util.PaymentTransactionType;

import java.util.UUID;

public interface PaymentService {
    void createPaymentTransaction(double depositAmount, UUID walletId, PaymentTransactionType replenishment);
}
