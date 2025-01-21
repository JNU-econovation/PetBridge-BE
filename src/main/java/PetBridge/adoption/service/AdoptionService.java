package PetBridge.adoption.service;

import PetBridge.adoption.repository.AdoptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdoptionService {
    private final AdoptionRepository adoptionRepository;
}
