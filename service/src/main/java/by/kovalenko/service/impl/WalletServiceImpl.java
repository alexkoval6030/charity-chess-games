package by.kovalenko.service.impl;

import by.kovalenko.dto.WalletDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.StakeEntity;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.entity.WalletEntity;
import by.kovalenko.mapper.WalletMapper;
import by.kovalenko.repositories.WalletRepository;
import by.kovalenko.service.PaymentService;
import by.kovalenko.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static by.kovalenko.util.PaymentTransactionType.*;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final static double COEFFICIENT_CHARITY_FUND = 0.75;
    private final static double COEFFICIENT_PLAYERS_FUND = 0.2;

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
        walletEntity.get().setAvailableMoney(walletEntity.get().getAvailableMoney() + depositAmount);
        paymentService.createPaymentTransaction(depositAmount, walletId, REPLENISHMENT);
        return walletMapper.walletEntityToWalletDto(walletEntity.get());
    }

    @Override
    public WalletDto addCharityFund(UUID walletId, double charityFund) {
        Optional<WalletEntity> walletCharityEntity = walletRepository.findById(walletId);
        walletCharityEntity.get().setAvailableMoney(walletCharityEntity.get().getAvailableMoney() + charityFund);
        paymentService.createPaymentTransaction(charityFund, walletCharityEntity.get().getId(), PAYED_CHARITY);
        return walletMapper.walletEntityToWalletDto(walletCharityEntity.get());
    }

    @Override
    public WalletDto addFeeFund(UUID walletId, double organizationFund) {
        Optional<WalletEntity> walletFeeEntity = walletRepository.findById(walletId);
        walletFeeEntity.get().setReservedMoney(walletFeeEntity.get().getReservedMoney() + organizationFund);
        paymentService.createPaymentTransaction(organizationFund, walletFeeEntity.get().getId(), PAYED_FEE);
        return walletMapper.walletEntityToWalletDto(walletFeeEntity.get());
    }

    @Override
    public WalletDto addPlayerFund(UserEntity userEntity, GameEntity gameEntity, Double coefficient) {
        WalletEntity walletPlayerEntity = userEntity.getWallet();
        List<StakeEntity> stakes = gameEntity.getStakes();
        Optional<StakeEntity> first = stakes.stream()
                .filter(stake -> stake.getUser().getUsername().equals(userEntity.getUsername()))
                .findFirst();
        walletPlayerEntity.setReservedMoney(walletPlayerEntity.getReservedMoney() - first.get().getStake());
        walletPlayerEntity.setAvailableMoney(walletPlayerEntity.getAvailableMoney() + ((first.get().getStake() * coefficient)));
        paymentService.createPaymentTransaction(first.get().getStake() * coefficient, walletPlayerEntity.getId(), PAYED_WINNERS);
        return walletMapper.walletEntityToWalletDto(walletPlayerEntity);
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

    @Override
    public Double chargeOffReserveAccount(UserEntity user, GameEntity gameEntity) {
        WalletEntity walletUserEntity = user.getWallet();
        List<StakeEntity> stakes = gameEntity.getStakes();
        Optional<StakeEntity> first = stakes.stream()
                .filter(stake -> stake.getUser().getUsername().equals(user.getUsername()))
                .findFirst();
        walletUserEntity.setReservedMoney(walletUserEntity.getReservedMoney() - first.get().getStake());
        paymentService.createPaymentTransaction(first.get().getStake(), walletUserEntity.getId(), PAYED_CHARITY);
        return first.get().getStake();
    }

    @Override
    public Double formationOfFund(UserEntity user, GameEntity gameEntity) {
        List<StakeEntity> stakes = gameEntity.getStakes();
        Optional<StakeEntity> first = stakes.stream()
                .filter(stake -> stake.getUser().getUsername().equals(user.getUsername()))
                .findFirst();
        return first.get().getStake();
    }

    @Override
    public WalletDto fundAllocation(Double fund, GameEntity gameEntity, UUID walletId) {
        double charityFund = fund * COEFFICIENT_CHARITY_FUND;
        double playersFund = fund * COEFFICIENT_PLAYERS_FUND;
        double feeFund = fund - charityFund - playersFund;
        WalletDto walletDto = addCharityFund(walletId, charityFund);
        addFeeFund(walletId, feeFund);
        List<StakeEntity> stakes = gameEntity.getStakes();
        double bettingAmount = stakes.stream().mapToDouble(StakeEntity::getStake).sum() - gameEntity.getCreatorStake().getStake();
        double coefficient = playersFund / bettingAmount;
        Set<UserEntity> users = gameEntity.getUsers();
        for (UserEntity user : users) {
            addPlayerFund(user, gameEntity, coefficient);
        }
        return walletDto;
    }
}
