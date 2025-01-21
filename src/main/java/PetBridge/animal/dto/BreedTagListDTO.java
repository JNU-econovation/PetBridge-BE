package PetBridge.animal.dto;

import PetBridge.animal.model.entity.Breed;

import java.util.List;

public record BreedTagListDTO(
        List<Breed> animalBreedList
) {
}
