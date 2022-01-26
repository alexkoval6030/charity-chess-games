package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "game_details")
@AttributeOverride(name = "id", column = @Column(name = "game_details_id"))
public class GameDetailsEntity extends BaseEntity{
    private UserEntity user;
}
