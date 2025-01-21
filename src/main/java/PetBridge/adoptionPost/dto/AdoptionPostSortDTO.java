package PetBridge.adoptionPost.dto;

public record AdoptionPostSortDTO(
        Long postId,
        String title,
        String subTitle,
        Long clickCount,
        Long wishCount,
        Boolean isWishPost
) {
}