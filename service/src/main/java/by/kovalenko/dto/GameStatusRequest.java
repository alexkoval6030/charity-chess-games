package by.kovalenko.dto;

import by.kovalenko.util.GameStatusList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class GameStatusRequest {
    private GameStatusList status;
    private LocalDate date;
}
