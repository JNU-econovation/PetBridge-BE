package PetBridge.adoption.dto.req;

import PetBridge.adoption.model.entity.Adoption;
import PetBridge.adoptionPost.model.entity.AdoptionPost;

public record AdoptionReq(
        String adoptionSeekerRequirement,
        String adoptionSeekerPhoneNumber
) {
    public Adoption toEntity (AdoptionPost adoptionPost) {
        return Adoption.builder()
                .adoptionPost(adoptionPost)
                .adoptionSeekerRequirement(adoptionSeekerRequirement)
                .adoptionSeekerPhoneNumber(adoptionSeekerPhoneNumber)
                .petOwnerPhoneNumber(null)
                .build();
    }
}
