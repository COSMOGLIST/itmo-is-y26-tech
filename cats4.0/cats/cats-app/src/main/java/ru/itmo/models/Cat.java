package ru.itmo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private Color color;
    @Column
    private Breed breed;
    @Column
    private LocalDate birthDate;
    @Column
    private int ownerId;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="cats_friends",
            joinColumns=@JoinColumn (name="cat1_id", referencedColumnName = "id"),
            inverseJoinColumns=@JoinColumn(name="cat2_id", referencedColumnName = "id"))
    private List<Cat> friends;

    public Cat(String name, Color color, Breed breed, LocalDate birthDate, int ownerId) {
        this.name = name;
        this.color = color;
        this.breed = breed;
        this.birthDate = birthDate;
        this.ownerId = ownerId;
    }
    public void addFriend(Cat friend) {
        friends.add(friend);
    }
    public void dropFriend(Cat friend) {
        for (Cat friend1 : friends) {
            if (friend1.id == friend.id) {
                friends.remove(friend1);
                break;
            }
        }
    }
}
