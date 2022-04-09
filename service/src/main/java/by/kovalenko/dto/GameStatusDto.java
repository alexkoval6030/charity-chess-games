package by.kovalenko.dto;

import by.kovalenko.util.GameStatusName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GameStatusDto {
    private UUID id;
    private GameStatusName status;
    private LocalDate date;
}
