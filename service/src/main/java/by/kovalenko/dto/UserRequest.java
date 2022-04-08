package by.kovalenko.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
}
