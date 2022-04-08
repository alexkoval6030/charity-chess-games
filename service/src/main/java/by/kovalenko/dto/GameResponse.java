package by.kovalenko.dto;

import by.kovalenko.entity.GameStatusEntity;
import by.kovalenko.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GameResponse {
    private UUID id;
    private UserEntity user;
    private GameStatusEntity gameStatus_id;
}
