package PetBridge.adoptionPost.dto;

public record AdoptionPostDetailDTO(
        Long id,
        String title,
        String subTitle,
        Long weight,
        Long age,
        Boolean isNeutered,
        Boolean isAdoptionContractRequired,
        String meetingPlace,
        String likes,
        String hates,
        String currentDiseases,
        String pastDiseases,
        String petOwnerRequirement,
        String detailContent,
        Boolean adoptionFinalizationStatus,
        Long clickCount,
        Long wishCount,
        String breedName, // 품종 정보
        String memberNickname // 작성자 닉네임
) {}