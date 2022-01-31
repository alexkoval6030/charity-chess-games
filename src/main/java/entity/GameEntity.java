package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "game")
@AttributeOverride(name = "id", column = @Column(name = "game_id"))
@NoArgsConstructor
@Getter
@Setter
public class GameEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "creator")
    private UserEntity user;
    @OneToOne
    @JoinColumn(name = "game_status_id")
    private GameStatusEntity gameStatus_id;
    @Column(name = "is_creator_win")
    private Boolean isCreatorWin;
    @ManyToMany
    @JoinTable(name = "game_user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Set<UserEntity> userGroup = new HashSet<>();
}
