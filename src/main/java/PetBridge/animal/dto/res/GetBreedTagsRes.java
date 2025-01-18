package PetBridge.animal.dto.res;

import PetBridge.animal.dto.BreedTagsDTO;
import PetBridge.animal.model.entity.Breed;

import java.util.List;

public record GetBreedTagsRes(
        List<Breed> animalBreedList
) {
    public static GetBreedTagsRes from(BreedTagsDTO breedTagsDTO) {
        return new GetBreedTagsRes(breedTagsDTO.animalBreedList());
    }
}
