package PetBridge.animal.service;

import PetBridge.animal.dto.AnimalTagsDTO;
import PetBridge.animal.dto.BreedTagsDTO;
import PetBridge.animal.model.entity.Breed;
import PetBridge.animal.model.entity.Tag;
import PetBridge.animal.repository.BreedRepository;
import PetBridge.animal.repository.TagAdoptionPostMappingRepository;
import PetBridge.animal.repository.TagBreedMappingRepository;
import PetBridge.animal.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AnimalService {
    private final String GENDER = "성별";
    private final String INOCULATION = "접종 내역";
    private final String TYPE = "동물 종류";
    private final String SIZE = "동물 크기";

    private BreedRepository breedRepository;
    private TagAdoptionPostMappingRepository tagAdoptionPostMappingRepository;
    private TagBreedMappingRepository tagBreedMappingRepository;
    private TagRepository tagRepository;

    @Transactional(readOnly = true)
    public AnimalTagsDTO getAnimalTags() {
        List<Tag> genderTagList = tagRepository.findByAttribute(GENDER);
        List<Tag> inoculationTagList = tagRepository.findByAttribute(INOCULATION);
        List<Tag> animalTypeTagList = tagRepository.findByAttribute(TYPE);
        List<Tag> animalSizeTagList = tagRepository.findByAttribute(SIZE);

        return new AnimalTagsDTO(
                genderTagList,
                inoculationTagList,
                animalTypeTagList,
                animalSizeTagList);
    }

    @Transactional(readOnly = true)
    public BreedTagsDTO getBreedTags() {
        List<Breed> animalBreedList = breedRepository.findAll();

        return new BreedTagsDTO(animalBreedList);
    }
}
