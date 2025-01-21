package PetBridge.animal.service;

import PetBridge.animal.dto.AnimalTagListDTO;
import PetBridge.animal.dto.BreedTagListDTO;
import PetBridge.animal.model.entity.Breed;
import PetBridge.animal.model.entity.Tag;
import PetBridge.animal.repository.BreedRepository;
import PetBridge.adoptionPost.repository.TagAdoptionPostMappingRepository;
import PetBridge.animal.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AnimalService {
    private static final String GENDER = "성별";
    private static final String INOCULATION = "접종 내역";
    private static final String TYPE = "동물 종류";
    private static final String SIZE = "동물 크기";

    private final BreedRepository breedRepository;
    private final TagAdoptionPostMappingRepository tagAdoptionPostMappingRepository;
    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public AnimalTagListDTO getAnimalTags() {
        List<Tag> genderTagList = tagRepository.findByAttribute(GENDER);
        List<Tag> inoculationTagList = tagRepository.findByAttribute(INOCULATION);
        List<Tag> animalTypeTagList = tagRepository.findByAttribute(TYPE);
        List<Tag> animalSizeTagList = tagRepository.findByAttribute(SIZE);

        return new AnimalTagListDTO(
                genderTagList,
                inoculationTagList,
                animalTypeTagList,
                animalSizeTagList);
    }

    @Transactional(readOnly = true)
    public BreedTagListDTO getBreedTags() {
        List<Breed> animalBreedList = breedRepository.findAll();

        return new BreedTagListDTO(animalBreedList);
    }
}
