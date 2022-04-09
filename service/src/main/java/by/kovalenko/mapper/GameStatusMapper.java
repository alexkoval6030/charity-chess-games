package by.kovalenko.mapper;

import by.kovalenko.dto.GameStatusDto;
import by.kovalenko.entity.GameStatusEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameStatusMapper {
    GameStatusDto gameStatusEntityToGameStatusDto(GameStatusEntity gameStatusEntity);

    GameStatusEntity gameStatusDtoToGameStatusEntity(GameStatusDto gameStatusDto);
}
