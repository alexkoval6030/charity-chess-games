package by.kovalenko.mapper;

import by.kovalenko.dto.GameStatusRequest;
import by.kovalenko.dto.GameStatusResponse;
import by.kovalenko.entity.GameStatusEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameStatusMapper {
    GameStatusResponse gameStatusEntityToGameStatusResponse(GameStatusEntity gameStatusEntity);

    GameStatusEntity gameStatusRequestToGameStatusEntity(GameStatusRequest gameStatusRequest);

    GameStatusEntity gameStatusResponseToGameStatusEntity(GameStatusResponse gameStatusResponse);
}
