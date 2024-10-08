package ru.itmo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.models.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
