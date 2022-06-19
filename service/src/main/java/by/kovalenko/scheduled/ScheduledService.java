package by.kovalenko.scheduled;

import by.kovalenko.entity.GameEntity;
import by.kovalenko.service.GameService;
import by.kovalenko.service.WalletService;
import by.kovalenko.util.GameStatusName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@EnableScheduling
@RequiredArgsConstructor
@Conditional(ScheduledCondition.class)
@Slf4j
public class ScheduledService {

    private static final int GAME_DELAY = 3;
    private static final int SCHEDULER_DELAY_MILLIS = 1 * 60 * 1000;

    private final GameService gameService;
    private final WalletService walletService;

    @PostConstruct
    public void init(){
        log.info("SCHEDULER ENABLE ON");
    }

    @Scheduled(fixedDelay = SCHEDULER_DELAY_MILLIS)
    @Transactional
    public void execute() {

        //logging

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, (-1) * GAME_DELAY);
        Date date = calendar.getTime();

        List<GameEntity> plannedGames = gameService.findAllByStatusAndCreatedBefore(
                GameStatusName.PLANNED, date);

        for (GameEntity game : plannedGames) {
            if (game.getUsers().isEmpty()) {
                game.getGameStatus().setGameStatusName(GameStatusName.CANCELED);
                walletService.returnStakeCancelledGame(game);
            } else {
                game.getGameStatus().setGameStatusName(GameStatusName.DURING);
            }
        }
    }
}
