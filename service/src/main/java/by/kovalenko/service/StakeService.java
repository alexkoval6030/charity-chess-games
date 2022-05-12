package by.kovalenko.service;

import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.StakeEntity;

import java.util.UUID;

public interface StakeService {
    StakeEntity makeStake(UUID userId, GameEntity game, double creationBet);
}
