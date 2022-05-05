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
    @Mapping(target = "creatorStake", source = "creatorStake", qualifiedByName = "creatorStakeMapper")
    GameDto gameEntityToGameDto(GameEntity gameEntity);
    @Named("creatorStakeMapper")
    default Double mapCreatorStake(StakeEntity stake) {
        if (stake == null) {
            return null;
        }
        return stake.getStake();
    }
}
