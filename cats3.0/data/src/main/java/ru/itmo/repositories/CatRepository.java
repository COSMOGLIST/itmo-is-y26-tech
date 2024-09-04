package ru.itmo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.models.Breed;
import ru.itmo.models.Cat;
import ru.itmo.models.Color;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
    List<Cat> findCatByBreed(Breed breed);
    List<Cat> findCatByColor(Color color);
    List<Cat> findCatByColorAndBreed(Color color, Breed breed);
    Cat findCatByIdAndOwnerId(int id, int owner_id);
    List<Cat> findCatByOwnerId(int owner_id);
    List<Cat> findCatByColorAndOwnerId(Color color, int owner_id);
    List<Cat> findCatByBreedAndOwnerId(Breed breed, int owner_id);
    List<Cat> findCatByColorAndBreedAndOwnerId(Color color, Breed breed, int owner_id);
}
