package PetBridge.animal.dto.res;

import PetBridge.animal.dto.AnimalTagsDTO;
import PetBridge.animal.model.entity.Tag;

import java.util.List;

public record GetAnimalTagsRes (
        List<Tag> genderTagList,
        List<Tag> inoculationTagList,
        List<Tag> animalTypeTagList,
        List<Tag> animalSizeTagList
) {
    public static GetAnimalTagsRes from(AnimalTagsDTO animalTagsDTO) {
        return new GetAnimalTagsRes(
                animalTagsDTO.genderTagList(),
                animalTagsDTO.inoculationTagList(),
                animalTagsDTO.animalTypeTagList(),
                animalTagsDTO.animalSizeTagList());
    }
}
