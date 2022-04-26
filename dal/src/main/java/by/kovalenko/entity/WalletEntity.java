package by.kovalenko.entity;

import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wallet")
@AttributeOverride(name = "id", column = @Column(name = "wallet_id"))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WalletEntity extends BaseEntity {
    @Column(name = "availableMoney")
    private Double availableMoney;
    @Column(name = "reservedMoney")
    private Double reservedMoney;
}
