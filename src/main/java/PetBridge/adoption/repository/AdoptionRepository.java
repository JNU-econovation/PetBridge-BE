package PetBridge.adoption.repository;

import PetBridge.adoption.model.entity.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}
