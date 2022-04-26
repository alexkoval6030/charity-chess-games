package by.kovalenko.mapper;

import by.kovalenko.dto.GameDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.StakeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.List;

@Mapper(componentModel = "spring", uses = {
        GameStatusMapper.class
})
public interface GameMapper {
    @Mapping(target = "creatorStake", source = "stakes", qualifiedByName = "creatorStakeMapper")
    GameDto gameEntityToGameDto(GameEntity gameEntity);
    @Named("creatorStakeMapper")
    default Double mapCreatorStake(List<StakeEntity> stakes) {
        if (stakes == null) {
            return null;
        }
        return stakes.isEmpty() ? null : stakes.get(0).getStake();
    }

    GameEntity gameDtoToGameEntity(GameDto gameDto);

    List<GameDto> listGameEntityToListGameDto(List<GameEntity> gameEntityList);

    HashSet<GameDto> hashSetGameEntityToHashSetGameDto(HashSet<GameEntity> gameEntityHashSet);
}
