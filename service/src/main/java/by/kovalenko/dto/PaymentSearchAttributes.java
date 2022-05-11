package by.kovalenko.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSearchAttributes {
    private String username;
    private String paymentTransactionType;
    private Date fromDate;
    private Date toDate;
    private Double minimum;
    private Double maximum;
}
