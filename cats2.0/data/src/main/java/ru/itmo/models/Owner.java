package ru.itmo.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private LocalDate birthDate;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Cat> cats;
    public Owner(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Owner() {
    }

    public void addCat(Cat cat) {
        cats.add(cat);
    }
    public void dropCat(Cat cat) {
        for (Cat cat1 : cats) {
            if (cat1.getId() == cat.getId()) {
                cats.remove(cat1);
                break;
            }
        }
    }
}

