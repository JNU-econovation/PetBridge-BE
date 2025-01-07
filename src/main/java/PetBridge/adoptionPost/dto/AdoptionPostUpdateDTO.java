package PetBridge.adoptionPost.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionPostUpdateDTO {
    @NotBlank(message = "제목은 필수 입력 사항입니다.")
    private String title;

    @NotBlank(message = "부제목은 필수 입력 사항입니다.")
    private String subTitle;

    @PositiveOrZero(message = "몸무게는 0 이상이어야 합니다.")
    private Long weight;

    @PositiveOrZero(message = "나이는 0 이상이어야 합니다.")
    private Long age;

    private Boolean isNeutered;

    private Boolean isAdoptionContractRequired;

    @NotBlank(message = "만남 장소는 필수 입력 사항입니다.")
    private String meetingPlace;

    @Size(max = 200, message = "좋아하는 내용은 최대 200자까지 입력 가능합니다.")
    private String likes;

    @Size(max = 200, message = "싫어하는 내용은 최대 200자까지 입력 가능합니다.")
    private String hates;

    @Size(max = 300, message = "현재 갖고 있는 질병 정보는 최대 300자까지 입력 가능합니다.")
    private String currentDiseases;

    @Size(max = 300, message = "과거 가졌던 질병 정보는 최대 300자까지 입력 가능합니다.")
    private String pastDiseases;

    @Size(max = 500, message = "반려 동물 주인 요구 사항은 최대 500자까지 입력 가능합니다.")
    private String petOwnerRequirement;

    @Size(max = 1000, message = "상세 내용은 최대 1000자까지 입력 가능합니다.")
    private String detailContent;

    //클라이언트에서 값을 전달하지 않으면, 엔티티에서 정의한 기본값(false)이 자동으로 적용됨
    private Boolean adoptionFinalizationStatus;
}


