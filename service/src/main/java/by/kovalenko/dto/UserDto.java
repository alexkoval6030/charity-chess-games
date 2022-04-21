package by.kovalenko.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private UUID id;

    @NotNull(message = "firstname cannot be null")
    @Min(value = 2, message = "firstname should not be less than 2 chars")
    private String firstname;

    @NotNull(message = "lastname cannot be null")
    @Min(value = 2, message = "lastname should not be less than 2 chars")
    private String lastname;

    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "username cannot be null")
    @Min(value = 2, message = "username should not be less than 2 chars")
    private String username;

    @NotNull(message = "password cannot be null")
    @Min(value = 7, message = "username should not be less than 2 chars")
    private String password;

    private WalletDto wallet;
}
