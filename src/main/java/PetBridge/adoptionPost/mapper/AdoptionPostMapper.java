package PetBridge.adoptionPost.mapper;

import PetBridge.adoptionPost.dto.AdoptionPostDTO;
import PetBridge.adoptionPost.dto.AdoptionPostDetailDTO;
import PetBridge.adoptionPost.dto.AdoptionPostSortDTO;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.animal.model.entity.Tag;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public AdoptionPostDetailDTO toAdoptionPostDetailDTO(AdoptionPost adoptionPost, String gender, List<Tag> inoculationList) {
        return new AdoptionPostDetailDTO(
                adoptionPost.getId(),
                adoptionPost.getBreed().getName(),
                gender,
                adoptionPost.getTitle(),
                adoptionPost.getSubTitle(),
                adoptionPost.getWeight(),
                adoptionPost.getAge(),
                adoptionPost.getIsNeutered(),
                adoptionPost.getIsAdoptionContractRequired(),
                inoculationList,
                adoptionPost.getMeetingPlace(),
                adoptionPost.getHates(),
                adoptionPost.getLikes(),
                adoptionPost.getCurrentDiseases(),
                adoptionPost.getPastDiseases(),
                adoptionPost.getPetOwnerRequirement(),
                adoptionPost.getDetailContent(),
                adoptionPost.getAdoptionFinalizationStatus()
        );
    }

    public AdoptionPostDTO toAdoptionPostDTO(AdoptionPost adoptionPost) {
        return new AdoptionPostDTO(
                adoptionPost.getId(),
                adoptionPost.getTitle(),
                adoptionPost.getSubTitle(),
                adoptionPost.getClickCount(),
                adoptionPost.getWishCount()
        );
    }
}
