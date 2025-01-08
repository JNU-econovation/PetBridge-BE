package PetBridge.adoptionPost.dto;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdoptionPostSortDTO {
    private Long postId;
    private String title;
    private String subTitle;
    private Long clickCount;
    private Long wishCount;
}