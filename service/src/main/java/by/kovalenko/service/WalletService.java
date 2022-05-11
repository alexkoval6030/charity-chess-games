package by.kovalenko.service;

import by.kovalenko.dto.WalletDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.entity.WalletEntity;

import java.util.UUID;

public interface WalletService {
    WalletDto findById(UUID id);

    WalletDto addFunds(UUID walletId, double depositAmount);

    WalletDto addCharityFund(UUID walletId, double charityFund);

    WalletDto addFeeFund(UUID walletId, double organizationFund);

    WalletDto addPlayerFund(UserEntity userEntity, GameEntity gameEntity, Double coefficient);

    WalletDto updateWalletMadeBet(UUID id, double creationBet);

    WalletEntity returnStakeCancelledGame(GameEntity gameEntity);

    Double chargeOffReserveAccount(UserEntity user, GameEntity gameEntity);

    Double formationOfFund(UserEntity user, GameEntity gameEntity);

    WalletDto fundAllocation(Double fund, GameEntity gameEntity, UUID walletId);
}
