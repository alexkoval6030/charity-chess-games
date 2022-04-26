package by.kovalenko.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GameDto {
    private UUID id;
    private UserDto creator;
    private GameStatusDto gameStatus;
    private Boolean isCreatorWin;
    private Set<UserDto> users;
    private Double creatorStake;
}
