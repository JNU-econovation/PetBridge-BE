package PetBridge.adoptionPost.repository;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.animal.model.entity.Breed;
import PetBridge.animal.model.entity.Tag;

import java.util.List;

public interface AdoptionPostCustom {
    List<AdoptionPost> findAllFilteredAdoptionPost (String sortBy, String keyword, List<Tag> tagList, List<Breed> breedList);
}
