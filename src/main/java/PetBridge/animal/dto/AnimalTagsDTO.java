package PetBridge.animal.dto;

import PetBridge.animal.model.entity.Tag;

import java.util.List;

public record AnimalTagsDTO (
        List<Tag> genderTagList,
        List<Tag> inoculationTagList,
        List<Tag> animalTypeTagList,
        List<Tag> animalSizeTagList
) {
}
