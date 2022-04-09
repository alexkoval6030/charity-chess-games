package by.kovalenko.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
}
