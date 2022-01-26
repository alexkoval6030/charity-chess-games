package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "game_status")
@AttributeOverride(name = "id", column = @Column(name = "game_status_id"))
@Getter
@Setter
public class GameStatusEntity extends BaseEntity{
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Enum status;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private LocalDate date;
}
