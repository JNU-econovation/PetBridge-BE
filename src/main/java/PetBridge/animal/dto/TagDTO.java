package PetBridge.animal.dto;

import PetBridge.animal.model.entity.Tag;

public record TagDTO(
        Long id,
        String attribute,
        String name
) {
    public static TagDTO from(Tag tag) {
        return new TagDTO(
                tag.getId(),
                tag.getAttribute(),
                tag.getName()
        );
    }
}
