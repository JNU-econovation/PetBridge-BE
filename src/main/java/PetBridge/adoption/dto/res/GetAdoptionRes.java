package PetBridge.adoption.dto.res;

import PetBridge.adoption.model.entity.Adoption;
import PetBridge.adoptionPost.model.entity.AdoptionPost;

public record GetAdoptionRes(
        Long id,
        Long adoptionPostId,
        String petOwnerRequirement,
        String petOwnerPhoneNumber,
        String adoptionSeekerRequirement,
        String adoptionSeekerPhoneNumber
) {
    public static GetAdoptionRes from(AdoptionPost adoptionPost, Adoption adoption) {
        String petOwnerRequirement =adoptionPost.getPetOwnerRequirement();
        String adoptionSeekerRequirement =adoption.getAdoptionSeekerRequirement();
        String petOwnerPhoneNumber =adoption.getPetOwnerPhoneNumber();
        String adoptionSeekerPhoneNumber =adoption.getAdoptionSeekerPhoneNumber();

        if(adoptionPost.getPetOwnerRequirement() == null)
            petOwnerRequirement = "없음";
        if(adoption.getAdoptionSeekerRequirement() == null)
            adoptionSeekerRequirement = "없음";
        if(adoption.getPetOwnerPhoneNumber() == null)
            petOwnerPhoneNumber = "";
        if(adoption.getAdoptionSeekerPhoneNumber() == null)
            adoptionSeekerPhoneNumber = "";

        return new GetAdoptionRes(
                adoption.getId(),
                adoptionPost.getId(),
                petOwnerRequirement,
                petOwnerPhoneNumber,
                adoptionSeekerRequirement,
                adoptionSeekerPhoneNumber
        );
    }
}
