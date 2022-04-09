package by.kovalenko.mapper;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.GameStatusDto;
import by.kovalenko.dto.UserDto;
import by.kovalenko.entity.GameEntity;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-09T14:11:36+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class GameMapperImpl implements GameMapper {

    @Override
    public GameDto gameEntityToGameDto(GameEntity gameEntity) {
        if ( gameEntity == null ) {
            return null;
        }

        UUID id = null;

        id = gameEntity.getId();

        UserDto user = null;
        GameStatusDto gameStatus_id = null;

        GameDto gameDto = new GameDto( id, user, gameStatus_id );

        return gameDto;
    }

    @Override
    public GameEntity gameDtoToGameEntity(GameDto gameDto) {
        if ( gameDto == null ) {
            return null;
        }

        GameEntity gameEntity = new GameEntity();

        gameEntity.setId( gameDto.getId() );

        return gameEntity;
    }
}
