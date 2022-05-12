package by.kovalenko.service.impl;

import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.StakeEntity;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.repositories.StakeRepository;
import by.kovalenko.repositories.UserRepository;
import by.kovalenko.service.StakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StakeServiceImpl implements StakeService {
    private final StakeRepository stakeRepository;
    private final UserRepository userRepository;

    @Override
    public StakeEntity makeStake(UUID userId, GameEntity game, double creationBet) {
        UserEntity user = userRepository.findById(userId).get();
        StakeEntity stakeEntity = new StakeEntity(creationBet, user, game);
        return stakeRepository.save(stakeEntity);
    }
}
