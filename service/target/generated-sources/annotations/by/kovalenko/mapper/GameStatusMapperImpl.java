package by.kovalenko.mapper;

import by.kovalenko.dto.GameStatusDto;
import by.kovalenko.entity.GameStatusEntity;
import by.kovalenko.util.GameStatusName;
import java.time.LocalDate;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-09T20:15:09+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class GameStatusMapperImpl implements GameStatusMapper {

    @Override
    public GameStatusDto gameStatusEntityToGameStatusDto(GameStatusEntity gameStatusEntity) {
        if ( gameStatusEntity == null ) {
            return null;
        }

        UUID id = null;
        GameStatusName gameStatusName = null;
        LocalDate date = null;

        id = gameStatusEntity.getId();
        gameStatusName = gameStatusEntity.getGameStatusName();
        date = gameStatusEntity.getDate();

        GameStatusDto gameStatusDto = new GameStatusDto( id, gameStatusName, date );

        return gameStatusDto;
    }

    @Override
    public GameStatusEntity gameStatusDtoToGameStatusEntity(GameStatusDto gameStatusDto) {
        if ( gameStatusDto == null ) {
            return null;
        }

        GameStatusEntity gameStatusEntity = new GameStatusEntity();

        gameStatusEntity.setId( gameStatusDto.getId() );
        gameStatusEntity.setGameStatusName( gameStatusDto.getGameStatusName() );
        gameStatusEntity.setDate( gameStatusDto.getDate() );

        return gameStatusEntity;
    }
}
