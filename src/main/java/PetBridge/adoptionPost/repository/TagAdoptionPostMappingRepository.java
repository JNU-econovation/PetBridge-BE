package PetBridge.adoptionPost.repository;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.model.entity.TagAdoptionPostMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagAdoptionPostMappingRepository extends JpaRepository<TagAdoptionPostMapping, Long> {
    List<TagAdoptionPostMapping> findMappingListByAdoptionPost(AdoptionPost adoptionPost);

    List<TagAdoptionPostMapping> findByAdoptionPost(AdoptionPost adoptionPost);
}
