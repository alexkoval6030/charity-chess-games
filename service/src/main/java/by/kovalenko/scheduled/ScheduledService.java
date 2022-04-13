package by.kovalenko.scheduled;

import by.kovalenko.entity.GameEntity;
import by.kovalenko.service.GameService;
import by.kovalenko.util.GameStatusName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledService {

    private static final int GAME_DELAY = 3;
    private static final int SCHEDULER_DELAY_MILLIS = 1 * 60 * 1000;

    private final GameService gameService;

    @Scheduled(fixedDelay = SCHEDULER_DELAY_MILLIS)
    @Transactional
    public void execute() {
        List<GameEntity> plannedGames = gameService.findAllByStatusAndCreatedBefore(
                GameStatusName.PLANNED, LocalDateTime.now().minusMinutes(GAME_DELAY));

        for (GameEntity game : plannedGames) {
            if (game.getUsers().isEmpty()) {
                game.getGameStatus().setGameStatusName(GameStatusName.CANCELED);
            } else {
                game.getGameStatus().setGameStatusName(GameStatusName.DURING);
            }
        }
    }
}
