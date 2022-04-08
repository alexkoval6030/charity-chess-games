package by.kovalenko.mapper;

import by.kovalenko.dto.GameRequest;
import by.kovalenko.dto.GameResponse;
import by.kovalenko.entity.GameEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameResponse gameEntityToGameResponse(GameEntity gameEntity);

    GameEntity gameRequestToGameEntity(GameRequest gameRequest);

    GameEntity gameResponseToGameEntity(GameResponse gameResponse);
}
