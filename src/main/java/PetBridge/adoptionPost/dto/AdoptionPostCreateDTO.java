package PetBridge.adoptionPost.dto;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.breed.model.entity.Breed;
import PetBridge.member.model.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionPostCreateDTO {
    @NotBlank(message = "제목은 필수 입력 사항입니다.")
    private String title;

    @NotBlank(message = "부제목은 필수 입력 사항입니다.")
    private String subTitle;

    private Long weight;
    private Long age;

    private Boolean isNeutered;
    private Boolean isAdoptionContractRequired;

    @NotBlank(message = "만남 장소는 필수 입력 사항입니다.")
    private String meetingPlace;

    private String likes;
    private String hates;
    private String currentDiseases;
    private String pastDiseases;

    private String petOwnerRequirement;
    private String detailContent;

    private Boolean adoptionFinalizationStatus;

    @NotNull(message = "회원 정보는 필수 입력 사항입니다.")
    private Member member;

    @NotNull(message = "품종 정보는 필수 입력 사항입니다.")
    private Breed breed;

    // DTO를 엔티티로 변환하는 메서드
    public AdoptionPost toEntity() {
        return AdoptionPost.builder()
                .title(this.title)
                .subTitle(this.subTitle)
                .weight(this.weight)
                .age(this.age)
                .isNeutered(this.isNeutered)
                .isAdoptionContractRequired(this.isAdoptionContractRequired)
                .meetingPlace(this.meetingPlace)
                .likes(this.likes)
                .hates(this.hates)
                .currentDiseases(this.currentDiseases)
                .pastDiseases(this.pastDiseases)
                .petOwnerRequirement(this.petOwnerRequirement)
                .detailContent(this.detailContent)
                .adoptionFinalizationStatus(this.adoptionFinalizationStatus)
                .member(this.member)
                .breed(this.breed)
                .build();
    }
}


