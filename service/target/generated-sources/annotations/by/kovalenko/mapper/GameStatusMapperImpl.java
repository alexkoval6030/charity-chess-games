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
    date = "2022-04-09T14:11:36+0300",
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
        LocalDate date = null;

        id = gameStatusEntity.getId();
        date = gameStatusEntity.getDate();

        GameStatusName status = null;

        GameStatusDto gameStatusDto = new GameStatusDto( id, status, date );

        return gameStatusDto;
    }

    @Override
    public GameStatusEntity gameStatusDtoToGameStatusEntity(GameStatusDto gameStatusDto) {
        if ( gameStatusDto == null ) {
            return null;
        }

        GameStatusEntity gameStatusEntity = new GameStatusEntity();

        gameStatusEntity.setId( gameStatusDto.getId() );
        gameStatusEntity.setDate( gameStatusDto.getDate() );

        return gameStatusEntity;
    }
}
