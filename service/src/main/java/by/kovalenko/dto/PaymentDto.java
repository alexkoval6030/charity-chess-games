package by.kovalenko.dto;

import by.kovalenko.util.PaymentTransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PaymentDto {
    private UUID id;
    private Date paymentDate;
    private PaymentTransactionType paymentTransactionType;
    private Double paymentSum;
}
