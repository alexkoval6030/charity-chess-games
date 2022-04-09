package by.kovalenko.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GameDto {
    private UUID id;
    private UserDto user;
    private GameStatusDto gameStatus_id;
}
