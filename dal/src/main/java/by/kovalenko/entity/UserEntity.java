package by.kovalenko.entity;

import by.kovalenko.util.UserRole;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"createdGames", "games"})
public class UserEntity extends BaseEntity {
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @ManyToMany(mappedBy = "userGroup", fetch = FetchType.LAZY)
    private Set<GameEntity> games;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private List<GameEntity> createdGames;
}
