package by.kovalenko.mapper;

import by.kovalenko.dto.GameStatusDto;
import by.kovalenko.entity.GameStatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameStatusMapper {
    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd HH:mm")
    GameStatusDto gameStatusEntityToGameStatusDto(GameStatusEntity gameStatusEntity);

    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd HH:mm")
    GameStatusEntity gameStatusDtoToGameStatusEntity(GameStatusDto gameStatusDto);
}
