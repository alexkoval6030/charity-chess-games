package by.kovalenko.service.impl;

import by.kovalenko.dto.GameStatusRequest;
import by.kovalenko.dto.GameStatusResponse;
import by.kovalenko.entity.GameStatusEntity;
import by.kovalenko.mapper.GameStatusMapper;
import by.kovalenko.repositories.GameStatusRepository;
import by.kovalenko.service.GameStatusService;
import by.kovalenko.util.GameStatusList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

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
    public GameStatusResponse createGameStatus() {
        GameStatusRequest gameStatusRequest = new GameStatusRequest(GameStatusList.PLANNED, LocalDate.now());
        GameStatusEntity gameStatusEntityFromService = gameStatusMapper.gameStatusRequestToGameStatusEntity(gameStatusRequest);
        GameStatusEntity save = gameStatusRepository.save(gameStatusEntityFromService);
        return gameStatusMapper.gameStatusEntityToGameStatusResponse(save);
    }
}
