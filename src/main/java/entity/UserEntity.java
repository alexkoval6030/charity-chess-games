package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@NoArgsConstructor
@Getter
@Setter
public class UserEntity extends BaseEntity{
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
    @ManyToMany
    private Set<GameEntity> games = new HashSet<>();
    @OneToMany
    private List<GameEntity> game = new ArrayList<GameEntity>();
}
