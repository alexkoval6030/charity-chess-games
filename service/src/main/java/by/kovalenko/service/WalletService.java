package by.kovalenko.service;

import by.kovalenko.dto.WalletDto;

import java.util.UUID;

public interface WalletService {
    WalletDto findById(UUID id);

    WalletDto addFunds(UUID id, double depositAmount);

    WalletDto updateWalletMadeBet(UUID id, double creationBet);
}
