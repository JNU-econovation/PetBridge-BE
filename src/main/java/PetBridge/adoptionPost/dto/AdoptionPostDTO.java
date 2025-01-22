package PetBridge.adoptionPost.dto;

public record AdoptionPostDTO (
        Long postId,
        String title,
        String subTitle,
        Long clickCount,
        Long wishCount
) {
}
