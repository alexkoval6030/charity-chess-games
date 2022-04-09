package by.kovalenko.mapper;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.GameStatusDto;
import by.kovalenko.dto.UserDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.GameStatusEntity;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.util.GameStatusName;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-09T20:15:09+0300",
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
        UserDto creator = null;
        GameStatusDto gameStatus = null;
        Boolean isCreatorWin = null;
        Set<UserDto> users = null;

        id = gameEntity.getId();
        creator = userEntityToUserDto( gameEntity.getCreator() );
        gameStatus = gameStatusEntityToGameStatusDto( gameEntity.getGameStatus() );
        isCreatorWin = gameEntity.getIsCreatorWin();
        users = userEntitySetToUserDtoSet( gameEntity.getUsers() );

        GameDto gameDto = new GameDto( id, creator, gameStatus, isCreatorWin, users );

        return gameDto;
    }

    @Override
    public GameEntity gameDtoToGameEntity(GameDto gameDto) {
        if ( gameDto == null ) {
            return null;
        }

        GameEntity gameEntity = new GameEntity();

        gameEntity.setId( gameDto.getId() );
        gameEntity.setCreator( userDtoToUserEntity( gameDto.getCreator() ) );
        gameEntity.setGameStatus( gameStatusDtoToGameStatusEntity( gameDto.getGameStatus() ) );
        gameEntity.setIsCreatorWin( gameDto.getIsCreatorWin() );
        gameEntity.setUsers( userDtoSetToUserEntitySet( gameDto.getUsers() ) );

        return gameEntity;
    }

    @Override
    public List<GameDto> listGameEntityToListGameDto(List<GameEntity> gameEntityList) {
        if ( gameEntityList == null ) {
            return null;
        }

        List<GameDto> list = new ArrayList<GameDto>( gameEntityList.size() );
        for ( GameEntity gameEntity : gameEntityList ) {
            list.add( gameEntityToGameDto( gameEntity ) );
        }

        return list;
    }

    protected UserDto userEntityToUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UUID id = null;
        String firstname = null;
        String lastname = null;
        String email = null;
        String username = null;
        String password = null;

        id = userEntity.getId();
        firstname = userEntity.getFirstname();
        lastname = userEntity.getLastname();
        email = userEntity.getEmail();
        username = userEntity.getUsername();
        password = userEntity.getPassword();

        UserDto userDto = new UserDto( id, firstname, lastname, email, username, password );

        return userDto;
    }

    protected GameStatusDto gameStatusEntityToGameStatusDto(GameStatusEntity gameStatusEntity) {
        if ( gameStatusEntity == null ) {
            return null;
        }

        UUID id = null;
        GameStatusName gameStatusName = null;
        LocalDate date = null;

        id = gameStatusEntity.getId();
        gameStatusName = gameStatusEntity.getGameStatusName();
        date = gameStatusEntity.getDate();

        GameStatusDto gameStatusDto = new GameStatusDto( id, gameStatusName, date );

        return gameStatusDto;
    }

    protected Set<UserDto> userEntitySetToUserDtoSet(Set<UserEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserDto> set1 = new HashSet<UserDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserEntity userEntity : set ) {
            set1.add( userEntityToUserDto( userEntity ) );
        }

        return set1;
    }

    protected UserEntity userDtoToUserEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( userDto.getId() );
        userEntity.setFirstname( userDto.getFirstname() );
        userEntity.setLastname( userDto.getLastname() );
        userEntity.setEmail( userDto.getEmail() );
        userEntity.setUsername( userDto.getUsername() );
        userEntity.setPassword( userDto.getPassword() );

        return userEntity;
    }

    protected GameStatusEntity gameStatusDtoToGameStatusEntity(GameStatusDto gameStatusDto) {
        if ( gameStatusDto == null ) {
            return null;
        }

        GameStatusEntity gameStatusEntity = new GameStatusEntity();

        gameStatusEntity.setId( gameStatusDto.getId() );
        gameStatusEntity.setGameStatusName( gameStatusDto.getGameStatusName() );
        gameStatusEntity.setDate( gameStatusDto.getDate() );

        return gameStatusEntity;
    }

    protected Set<UserEntity> userDtoSetToUserEntitySet(Set<UserDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserEntity> set1 = new HashSet<UserEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserDto userDto : set ) {
            set1.add( userDtoToUserEntity( userDto ) );
        }

        return set1;
    }
}
