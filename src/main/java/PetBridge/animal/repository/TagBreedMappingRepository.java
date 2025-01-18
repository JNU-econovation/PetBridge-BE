package PetBridge.animal.repository;

import PetBridge.animal.model.entity.TagBreedMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagBreedMappingRepository extends JpaRepository<TagBreedMapping, Long> {
}
