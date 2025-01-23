package PetBridge.adoptionPost.repository;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.model.entity.AdoptionPostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdoptionPostImageRepository extends JpaRepository<AdoptionPostImage, Long> {
    Optional<AdoptionPostImage> findByAdoptionPost(AdoptionPost adoptionPost);
}
