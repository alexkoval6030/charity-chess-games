package by.kovalenko.entity;

import by.kovalenko.util.GameStatusName;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_status")
@AttributeOverride(name = "id", column = @Column(name = "game_status_id"))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GameStatusEntity extends BaseEntity{
    @Column(name = "game_status_name")
    @Enumerated(EnumType.STRING)
    private GameStatusName gameStatusName;
    @Column(name = "date")
    private LocalDateTime date;
}
