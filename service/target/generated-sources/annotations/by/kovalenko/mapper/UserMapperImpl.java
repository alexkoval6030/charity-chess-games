package by.kovalenko.mapper;

import by.kovalenko.dto.UserRequest;
import by.kovalenko.dto.UserResponse;
import by.kovalenko.entity.UserEntity;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-08T20:27:02+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse userEntityToUserResponse(UserEntity userEntity) {
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

        UserResponse userResponse = new UserResponse( id, firstname, lastname, email, username, password );

        return userResponse;
    }

    @Override
    public UserEntity userRequestToUserEntity(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setFirstname( userRequest.getFirstname() );
        userEntity.setLastname( userRequest.getLastname() );
        userEntity.setEmail( userRequest.getEmail() );
        userEntity.setUsername( userRequest.getUsername() );
        userEntity.setPassword( userRequest.getPassword() );

        return userEntity;
    }

    @Override
    public UserEntity userResponseToUserEntity(UserResponse userResponse) {
        if ( userResponse == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( userResponse.getId() );
        userEntity.setFirstname( userResponse.getFirstname() );
        userEntity.setLastname( userResponse.getLastname() );
        userEntity.setEmail( userResponse.getEmail() );
        userEntity.setUsername( userResponse.getUsername() );
        userEntity.setPassword( userResponse.getPassword() );

        return userEntity;
    }
}
