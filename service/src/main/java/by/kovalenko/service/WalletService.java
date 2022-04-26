package by.kovalenko.service;

import by.kovalenko.dto.WalletDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.WalletEntity;

import java.util.UUID;

public interface WalletService {
    WalletDto findById(UUID id);

    WalletDto addFunds(UUID id, double depositAmount);

    WalletDto updateWalletMadeBet(UUID id, double creationBet);

    WalletEntity returnStakeCancelledGame(GameEntity gameEntity);
}
