package PetBridge.adoptionPost.repository;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.animal.model.entity.Breed;
import PetBridge.animal.model.entity.Tag;
import PetBridge.search.model.entity.SearchCondition;

import java.util.List;

public interface AdoptionPostCustom {
    List<AdoptionPost> findAllFilteredAdoptionPost (SearchCondition searchCondition, List<Tag> tagList, List<Breed> breedList);
}
