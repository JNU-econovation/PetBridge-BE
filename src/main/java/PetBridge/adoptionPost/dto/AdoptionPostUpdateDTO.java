package PetBridge.adoptionPost.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionPostUpdateDTO {
    private String title;
    private String subTitle;
    private Long weight;
    private Long age;
    private Boolean isNeutered;
    private Boolean isAdoptionContractRequired;
    private String meetingPlace;
    private String likes;
    private String hates;
    private String currentDiseases;
    private String pastDiseases;
    private String petOwnerRequirement;
    private String detailContent;
    private Boolean adoptionFinalizationStatus;
}


