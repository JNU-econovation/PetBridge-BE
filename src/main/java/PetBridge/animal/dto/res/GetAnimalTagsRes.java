package PetBridge.animal.dto.res;

import PetBridge.animal.dto.AnimalTagListDTO;
import PetBridge.animal.model.entity.Tag;

import java.util.List;

public record GetAnimalTagsRes (
        List<Tag> genderTagList,
        List<Tag> inoculationTagList,
        List<Tag> animalTypeTagList,
        List<Tag> animalSizeTagList
) {
    public static GetAnimalTagsRes from(AnimalTagListDTO animalTagListDTO) {
        return new GetAnimalTagsRes(
                animalTagListDTO.genderTagList(),
                animalTagListDTO.inoculationTagList(),
                animalTagListDTO.animalTypeTagList(),
                animalTagListDTO.animalSizeTagList());
    }
}
