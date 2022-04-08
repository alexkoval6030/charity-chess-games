package by.kovalenko.dto;

import by.kovalenko.util.GameStatusList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GameStatusResponse {
    private UUID id;
    private GameStatusList status;
    private LocalDate date;
}
