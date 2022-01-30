package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.GameStatusList;

import java.time.LocalDate;

@Entity
@Table(name = "game_status")
@AttributeOverride(name = "id", column = @Column(name = "game_status_id"))
@NoArgsConstructor
@Getter
@Setter
public class GameStatusEntity extends BaseEntity{
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private GameStatusList status;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private LocalDate date;
}
