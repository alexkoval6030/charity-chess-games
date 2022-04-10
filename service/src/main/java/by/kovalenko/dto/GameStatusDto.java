package by.kovalenko.dto;

import by.kovalenko.util.GameStatusName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GameStatusDto {
    private UUID id;
    private GameStatusName gameStatusName;
    private LocalDateTime date;
}
