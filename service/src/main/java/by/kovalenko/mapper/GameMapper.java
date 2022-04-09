package by.kovalenko.mapper;

import by.kovalenko.dto.GameDto;
import by.kovalenko.entity.GameEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameDto gameEntityToGameDto(GameEntity gameEntity);

    GameEntity gameDtoToGameEntity(GameDto gameDto);
}
