package PetBridge.wish.dto.res;

import PetBridge.adoptionPost.dto.AdoptionPostSortDTO;

import java.util.List;

public record GetWishPostListRes(
        List<AdoptionPostSortDTO> wishPostList
 ) {
}
