package by.kovalenko.entity;

import jakarta.persistence.*;
import lombok.*;
import by.kovalenko.service.GameStatusList;

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
