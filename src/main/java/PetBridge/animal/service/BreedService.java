package PetBridge.animal.service;

import PetBridge.animal.exception.breedException.badRequestException.BreedNotFoundException;
import PetBridge.animal.model.entity.Breed;
import PetBridge.animal.repository.BreedRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BreedService {
    private final BreedRepository breedRepository;

    @Transactional(readOnly = true)
    public List<Breed> findByIdListOrThrow(List<Long> breedIdList) {
        return breedIdList
                .stream()
                .map(this::findByIdOrThrow)
                .toList();
    }

    @Transactional(readOnly = true)
    public Breed findByIdOrThrow (Long breedId) {
        return breedRepository.findById(breedId)
                .orElseThrow(BreedNotFoundException::new);
    }
}
