package PetBridge.adoptionPost.mapper;

import PetBridge.adoptionPost.dto.AdoptionPostSortDTO;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class AdoptionPostMapper {

    public AdoptionPostSortDTO toAdoptionPostSortDTO(AdoptionPost adoptionPost, boolean isWishPost) {
        return new AdoptionPostSortDTO(
                adoptionPost.getId(),
                adoptionPost.getTitle(),
                adoptionPost.getSubTitle(),
                adoptionPost.getClickCount(),
                adoptionPost.getWishCount(),
                isWishPost
        );
    }
}
