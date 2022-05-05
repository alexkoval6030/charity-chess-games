package by.kovalenko.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "game")
@AttributeOverride(name = "id", column = @Column(name = "game_id"))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"creator", "users", "stakes"})
public class GameEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private UserEntity creator;
    @OneToOne
    @JoinColumn(name = "stake_id")
    private StakeEntity creatorStake;
    @OneToOne
    @JoinColumn(name = "game_status_id")
    private GameStatusEntity gameStatus;
    @Column(name = "is_creator_win")
    private Boolean isCreatorWin;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_game_link",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> users = new HashSet<>();

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private List<StakeEntity> stakes = new ArrayList<>();

    public GameEntity(UserEntity creator, GameStatusEntity gameStatus) {
        this.creator = creator;
        this.gameStatus = gameStatus;
    }
}
