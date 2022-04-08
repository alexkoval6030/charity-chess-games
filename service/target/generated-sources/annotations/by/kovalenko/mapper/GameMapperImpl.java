package by.kovalenko.mapper;

import by.kovalenko.dto.GameRequest;
import by.kovalenko.dto.GameResponse;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.GameStatusEntity;
import by.kovalenko.entity.UserEntity;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-08T20:27:02+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class GameMapperImpl implements GameMapper {

    @Override
    public GameResponse gameEntityToGameResponse(GameEntity gameEntity) {
        if ( gameEntity == null ) {
            return null;
        }

        UUID id = null;
        UserEntity user = null;
        GameStatusEntity gameStatus_id = null;

        id = gameEntity.getId();
        user = gameEntity.getUser();
        gameStatus_id = gameEntity.getGameStatus_id();

        GameResponse gameResponse = new GameResponse( id, user, gameStatus_id );

        return gameResponse;
    }

    @Override
    public GameEntity gameRequestToGameEntity(GameRequest gameRequest) {
        if ( gameRequest == null ) {
            return null;
        }

        GameEntity gameEntity = new GameEntity();

        gameEntity.setUser( gameRequest.getUser() );
        gameEntity.setGameStatus_id( gameRequest.getGameStatus_id() );

        return gameEntity;
    }

    @Override
    public GameEntity gameResponseToGameEntity(GameResponse gameResponse) {
        if ( gameResponse == null ) {
            return null;
        }

        GameEntity gameEntity = new GameEntity();

        gameEntity.setId( gameResponse.getId() );
        gameEntity.setUser( gameResponse.getUser() );
        gameEntity.setGameStatus_id( gameResponse.getGameStatus_id() );

        return gameEntity;
    }
}
