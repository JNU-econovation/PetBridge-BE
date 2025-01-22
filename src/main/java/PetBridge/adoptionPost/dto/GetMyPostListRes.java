package PetBridge.adoptionPost.dto;

import java.util.List;

public record GetMyPostListRes (
        List<AdoptionPostDTO> postList
) {
}
