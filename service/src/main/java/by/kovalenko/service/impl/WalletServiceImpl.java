package by.kovalenko.service.impl;

import by.kovalenko.dto.WalletDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.WalletEntity;
import by.kovalenko.mapper.WalletMapper;
import by.kovalenko.repositories.WalletRepository;
import by.kovalenko.service.PaymentService;
import by.kovalenko.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static by.kovalenko.util.PaymentTransactionType.*;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    private final PaymentService paymentService;

    @Override
    public WalletDto findById(UUID id) {
        Optional<WalletEntity> walletEntity = walletRepository.findById(id);
        return walletMapper.walletEntityToWalletDto(walletEntity.get());
    }

    @Override
    public WalletDto addFunds(UUID walletId, double depositAmount) {
        Optional<WalletEntity> walletEntity = walletRepository.findById(walletId);
        double accountUpdated = walletEntity.get().getAvailableMoney() + depositAmount;
        walletEntity.get().setAvailableMoney(accountUpdated);
        paymentService.createPaymentTransaction(depositAmount, walletId, REPLENISHMENT);
        return walletMapper.walletEntityToWalletDto(walletEntity.get());
    }

    @Override
    public WalletDto updateWalletMadeBet(UUID walletId, double creationBet) {
        Optional<WalletEntity> walletEntity = walletRepository.findById(walletId);
        walletEntity.get().setAvailableMoney(walletEntity.get().getAvailableMoney() - creationBet);
        walletEntity.get().setReservedMoney(walletEntity.get().getReservedMoney() + creationBet);
        paymentService.createPaymentTransaction(creationBet, walletId, BUY_IN);
        return walletMapper.walletEntityToWalletDto(walletEntity.get());
    }

    @Override
    public WalletEntity returnStakeCancelledGame(GameEntity gameEntity) {
        WalletEntity wallet = gameEntity.getCreator().getWallet();
        double stake = gameEntity.getStakes().get(0).getStake();
        wallet.setAvailableMoney(wallet.getAvailableMoney() + stake);
        wallet.setReservedMoney(wallet.getReservedMoney() - stake);
        paymentService.createPaymentTransaction(stake, wallet.getId(), CANCELLATION);

        return wallet;
    }
}
