package PetBridge.adoptionPost.dto;

import PetBridge.animal.model.entity.Tag;

import java.util.List;

public record AdoptionPostDetailDTO(
        Long id,
        String breed,
        String gender,
        String title,
        String subTitle,
        Double weight,
        Long age,
        Boolean isNeutered,
        Boolean isAdoptionContractRequired,
        List<Tag> inoculationList,
        String meetingPlace,
        String hates,
        String likes,
        String currentDiseases,
        String pastDiseases,
        String petOwnerRequirement,
        String detailContent,
        Boolean adoptionFinalizationStatus
) {
}