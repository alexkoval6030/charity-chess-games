package by.kovalenko.entity;

import by.kovalenko.util.PaymentTransactionType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment")
@AttributeOverride(name = "id", column = @Column(name = "payment_id"))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "user")
public class PaymentEntity extends BaseEntity {
    @Column(name = "paymentDate")
    private Date paymentDate;
    @Column(name = "paymentTransaction")
    @Enumerated(EnumType.STRING)
    private PaymentTransactionType paymentTransactionType;
    @Column(name = "paymentSum")
    private Double paymentSum;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;
}
