package by.kovalenko.mapper;

import by.kovalenko.dto.GameStatusRequest;
import by.kovalenko.dto.GameStatusResponse;
import by.kovalenko.entity.GameStatusEntity;
import by.kovalenko.util.GameStatusList;
import java.time.LocalDate;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-08T20:27:02+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class GameStatusMapperImpl implements GameStatusMapper {

    @Override
    public GameStatusResponse gameStatusEntityToGameStatusResponse(GameStatusEntity gameStatusEntity) {
        if ( gameStatusEntity == null ) {
            return null;
        }

        UUID id = null;
        GameStatusList status = null;
        LocalDate date = null;

        id = gameStatusEntity.getId();
        status = gameStatusEntity.getStatus();
        date = gameStatusEntity.getDate();

        GameStatusResponse gameStatusResponse = new GameStatusResponse( id, status, date );

        return gameStatusResponse;
    }

    @Override
    public GameStatusEntity gameStatusRequestToGameStatusEntity(GameStatusRequest gameStatusRequest) {
        if ( gameStatusRequest == null ) {
            return null;
        }

        GameStatusEntity gameStatusEntity = new GameStatusEntity();

        gameStatusEntity.setStatus( gameStatusRequest.getStatus() );
        gameStatusEntity.setDate( gameStatusRequest.getDate() );

        return gameStatusEntity;
    }

    @Override
    public GameStatusEntity gameStatusResponseToGameStatusEntity(GameStatusResponse gameStatusResponse) {
        if ( gameStatusResponse == null ) {
            return null;
        }

        GameStatusEntity gameStatusEntity = new GameStatusEntity();

        gameStatusEntity.setId( gameStatusResponse.getId() );
        gameStatusEntity.setStatus( gameStatusResponse.getStatus() );
        gameStatusEntity.setDate( gameStatusResponse.getDate() );

        return gameStatusEntity;
    }
}
