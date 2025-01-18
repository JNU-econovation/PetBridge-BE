package PetBridge.animal.repository;

import PetBridge.animal.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByAttribute(String gender);
}
