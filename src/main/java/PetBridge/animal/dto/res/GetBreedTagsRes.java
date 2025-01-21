package PetBridge.animal.dto.res;

import PetBridge.animal.dto.BreedTagListDTO;
import PetBridge.animal.model.entity.Breed;

import java.util.List;

public record GetBreedTagsRes(
        List<Breed> animalBreedList
) {
    public static GetBreedTagsRes from(BreedTagListDTO breedTagListDTO) {
        return new GetBreedTagsRes(breedTagListDTO.animalBreedList());
    }
}
