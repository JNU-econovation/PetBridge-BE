package PetBridge.adoptionPost.dto;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.animal.model.entity.Breed;
import PetBridge.member.model.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.concurrent.atomic.AtomicLong;

public record AdoptionPostCreateDTO (
    @NotBlank(message = "제목은 필수 입력 사항입니다.")
    String title,

    @NotBlank(message = "부제목은 필수 입력 사항입니다.")
    String subTitle,

    @PositiveOrZero(message = "몸무게는 0 이상이어야 합니다.")
    Long weight,

    @PositiveOrZero(message = "나이는 0 이상이어야 합니다.")
    Long age,

    Boolean isNeutered,

    Boolean isAdoptionContractRequired,

    @NotBlank(message = "만남 장소는 필수 입력 사항입니다.")
    String meetingPlace,

    @Size(max = 200, message = "좋아하는 내용은 최대 200자까지 입력 가능합니다.")
    String likes,

    @Size(max = 200, message = "싫어하는 내용은 최대 200자까지 입력 가능합니다.")
    String hates,

    @Size(max = 300, message = "현재 갖고 있는 질병 정보는 최대 300자까지 입력 가능합니다.")
    String currentDiseases,

    @Size(max = 300, message = "과거 가졌던 질병 정보는 최대 300자까지 입력 가능합니다.")
    String pastDiseases,

    @Size(max = 500, message = "반려 동물 주인 요구 사항은 최대 500자까지 입력 가능합니다.")
    String petOwnerRequirement,

    @Size(max = 1000, message = "상세 내용은 최대 1000자까지 입력 가능합니다.")
    String detailContent,

    //클라이언트에서 값을 전달하지 않으면, 엔티티에서 정의한 기본값(false)이 자동으로 적용됨
    Boolean adoptionFinalizationStatus,

    @NotNull(message = "분양 작성자는 필수 입력 사항입니다.")
    Long memberId,

    @NotNull(message = "품종 정보는 필수 입력 사항입니다.")
    Long breedId
){
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
                .member(Member.builder().id(this.memberId).build())
                .breed(Breed.builder().id(this.breedId).build())
                .wishCount(new AtomicLong(0))
                .clickCount(new AtomicLong(0))
                .build();
    }
}