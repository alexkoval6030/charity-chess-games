package by.kovalenko.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class WalletDto {
    private UUID id;
    private double availableMoney;
    private double reservedMoney;
}
