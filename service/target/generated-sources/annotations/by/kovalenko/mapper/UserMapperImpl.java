package by.kovalenko.mapper;

import by.kovalenko.dto.UserDto;
import by.kovalenko.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-01T13:32:23+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userEntityToUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        String firstname = null;
        String lastname = null;
        String email = null;
        String username = null;
        String password = null;

        firstname = userEntity.getFirstname();
        lastname = userEntity.getLastname();
        email = userEntity.getEmail();
        username = userEntity.getUsername();
        password = userEntity.getPassword();

        UserDto userDto = new UserDto( firstname, lastname, email, username, password );

        return userDto;
    }

    @Override
    public UserEntity userDtoToUserEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setFirstname( userDto.getFirstname() );
        userEntity.setLastname( userDto.getLastname() );
        userEntity.setEmail( userDto.getEmail() );
        userEntity.setUsername( userDto.getUsername() );
        userEntity.setPassword( userDto.getPassword() );

        return userEntity;
    }
}
