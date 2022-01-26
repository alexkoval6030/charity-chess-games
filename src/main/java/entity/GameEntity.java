package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "game")
@AttributeOverride(name = "id", column = @Column(name = "game_id"))
@Getter
@Setter
public class GameEntity extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "game_status_id")
    private GameStatusEntity gameStatus_id;
}
