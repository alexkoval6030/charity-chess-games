package by.kovalenko.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "game")
@AttributeOverride(name = "id", column = @Column(name = "game_id"))
@NoArgsConstructor
@AllArgsConstructor()
@Getter
@Setter
@ToString
public class GameEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToOne
    @JoinColumn(name = "game_status_id")
    private GameStatusEntity gameStatus_id;
    @Column(name = "is_creator_win")
    private Boolean isCreatorWin;
    @ManyToMany
    @JoinTable(name = "game_user_link",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Set<UserEntity> userGroup = new HashSet<>();
}
