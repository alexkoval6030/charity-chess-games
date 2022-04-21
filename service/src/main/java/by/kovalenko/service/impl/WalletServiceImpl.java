package by.kovalenko.service.impl;

import by.kovalenko.dto.WalletDto;
import by.kovalenko.entity.WalletEntity;
import by.kovalenko.mapper.WalletMapper;
import by.kovalenko.repositories.WalletRepository;
import by.kovalenko.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    @Override
    public WalletDto findById(UUID id) {
        Optional<WalletEntity> walletEntity = walletRepository.findById(id);
        return walletMapper.walletEntityToWalletDto(walletEntity.get());
    }

    @Override
    public WalletDto addFunds(UUID id, double depositAmount) {
        Optional<WalletEntity> walletEntity = walletRepository.findById(id);
        double accountUpdated = walletEntity.get().getAvailableMoney() + depositAmount;
        walletEntity.get().setAvailableMoney(accountUpdated);
        return walletMapper.walletEntityToWalletDto(walletEntity.get());
    }

    @Override
    public WalletDto updateWalletMadeBet(UUID id, double creationBet) {
        Optional<WalletEntity> walletEntity = walletRepository.findById(id);
        walletEntity.get().setAvailableMoney(walletEntity.get().getAvailableMoney() - creationBet);
        walletEntity.get().setReservedMoney(walletEntity.get().getReservedMoney() + creationBet);
        return walletMapper.walletEntityToWalletDto(walletEntity.get());
    }
}
