package by.kovalenko.entity;

import by.kovalenko.util.GameStatusList;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "game_status")
@AttributeOverride(name = "id", column = @Column(name = "game_status_id"))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GameStatusEntity extends BaseEntity{
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private GameStatusList status;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private LocalDate date;
}
