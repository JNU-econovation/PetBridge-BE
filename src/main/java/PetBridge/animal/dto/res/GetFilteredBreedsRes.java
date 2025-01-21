package PetBridge.animal.dto.res;

import PetBridge.animal.dto.BreedTagListDTO;
import PetBridge.animal.model.entity.Breed;

import java.util.List;

public record GetFilteredBreedsRes(
        List<Breed> animalBreedList
) {
    public static GetFilteredBreedsRes from(BreedTagListDTO breedTagListDTO) {
        return new GetFilteredBreedsRes(breedTagListDTO.animalBreedList());
    }
}
