package by.kovalenko.dto;

import by.kovalenko.entity.GameStatusEntity;
import by.kovalenko.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameRequest {
    private UserEntity user;
    private GameStatusEntity gameStatus_id;
}
