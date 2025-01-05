package PetBridge.adoptionPost.validator;

import PetBridge.adoptionPost.dto.AdoptionPostDTO;
import org.springframework.stereotype.Component;

//유효성검사
@Component
public class AdoptionPostValidator {

    public void validateUpdateDTO(AdoptionPostDTO adoptionPostDTO) {
        if (adoptionPostDTO.getTitle() != null && adoptionPostDTO.getTitle().isBlank()) {
            throw new IllegalArgumentException("제목은 비어 있을 수 없습니다.");
        }
        if (adoptionPostDTO.getWeight() != null && adoptionPostDTO.getWeight() <= 0) {
            throw new IllegalArgumentException("무게는 0보다 커야 합니다.");
        }
    }
}
