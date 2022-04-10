package by.kovalenko.service.impl;

import by.kovalenko.entity.GameStatusEntity;
import by.kovalenko.mapper.GameStatusMapper;
import by.kovalenko.repositories.GameStatusRepository;
import by.kovalenko.service.GameStatusService;
import by.kovalenko.util.GameStatusName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class GameStatusServiceImpl implements GameStatusService {
    private final GameStatusRepository gameStatusRepository;
    private final GameStatusMapper gameStatusMapper;

    public GameStatusServiceImpl(GameStatusRepository gameStatusRepository, GameStatusMapper gameStatusMapper) {
        this.gameStatusRepository = gameStatusRepository;
        this.gameStatusMapper = gameStatusMapper;
    }

    @Override
    public GameStatusEntity createGameStatus() {
        return gameStatusRepository.save(
                new GameStatusEntity(GameStatusName.PLANNED, LocalDateTime.now()));
    }
}
