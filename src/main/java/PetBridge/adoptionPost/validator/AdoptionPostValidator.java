package PetBridge.adoptionPost.validator;

import PetBridge.adoptionPost.dto.AdoptionPostCreateDTO;
import PetBridge.adoptionPost.dto.AdoptionPostUpdateDTO;
import org.springframework.stereotype.Component;

//유효성검사
@Component
public class AdoptionPostValidator {

    public void validateUpdateDTO(AdoptionPostUpdateDTO adoptionPostUpdateDTO) {
        if (adoptionPostUpdateDTO.getTitle() != null && adoptionPostUpdateDTO.getTitle().isBlank()) {
            throw new IllegalArgumentException("제목은 비어 있을 수 없습니다.");
        }
        if (adoptionPostUpdateDTO.getWeight() != null && adoptionPostUpdateDTO.getWeight() <= 0) {
            throw new IllegalArgumentException("무게는 0보다 커야 합니다.");
        }
    }
}
