package by.kovalenko.service.impl;

import by.kovalenko.entity.GameStatusEntity;
import by.kovalenko.repositories.GameStatusRepository;
import by.kovalenko.service.GameStatusService;
import by.kovalenko.util.GameStatusName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class GameStatusServiceImpl implements GameStatusService {
    private final GameStatusRepository gameStatusRepository;

    @Override
    public GameStatusEntity createGameStatus() {
        Date date = new Date();
        return gameStatusRepository.save(
                new GameStatusEntity(GameStatusName.PLANNED, date));
    }
}
