package PetBridge.animal.repository;

import PetBridge.animal.model.entity.Breed;
import PetBridge.animal.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreedRepository extends JpaRepository<Breed, Long> {
    List<Breed> findByAnimalType(Tag animalTypeTag);
}
