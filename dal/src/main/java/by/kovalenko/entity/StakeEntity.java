package by.kovalenko.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "stake")
@AttributeOverride(name = "id", column = @Column(name = "stake_id"))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StakeEntity extends BaseEntity {
    @Column(name = "stake")
    private Double stake;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    private GameEntity game;
}
