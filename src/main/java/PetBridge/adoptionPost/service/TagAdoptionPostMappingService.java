package PetBridge.adoptionPost.service;

import PetBridge.adoptionPost.exception.TagAdoptionPostMappingNotFoundException;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.model.entity.TagAdoptionPostMapping;
import PetBridge.adoptionPost.repository.TagAdoptionPostMappingRepository;
import PetBridge.animal.model.entity.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TagAdoptionPostMappingService {
    private static final String GENDER = "성별";
    private static final String INOCULATION = "접종 내역";

    private final TagAdoptionPostMappingRepository tagAdoptionPostMappingRepository;

    @Transactional(readOnly = true)
    public List<Tag> findInoculationTagListOfAdoptionPost(AdoptionPost adoptionPost) {
        List<TagAdoptionPostMapping> inoculationMapping = findMappingListByAdoptionPost(adoptionPost)
                .stream()
                .filter(tagAdoptionPostMapping -> INOCULATION.equals(tagAdoptionPostMapping.getTag().getAttribute()))
                .toList();

        return inoculationMapping
                .stream()
                .map(TagAdoptionPostMapping::getTag)
                .toList();
    }

    @Transactional(readOnly = true)
    public Tag findGenderTagOfAdoptionPost(AdoptionPost adoptionPost) {
        TagAdoptionPostMapping genderMapping = findMappingListByAdoptionPost(adoptionPost)
                .stream()
                .filter(tagAdoptionPostMapping -> GENDER.equals(tagAdoptionPostMapping.getTag().getAttribute()))
                .findFirst()
                .orElseThrow(TagAdoptionPostMappingNotFoundException::new);

        return genderMapping.getTag();
    }

    @Transactional(readOnly = true)
    public List<TagAdoptionPostMapping> findMappingListByAdoptionPost(AdoptionPost adoptionPost) {
        return tagAdoptionPostMappingRepository.findByAdoptionPost(adoptionPost);
    }

    @Transactional
    public void saveMapping(AdoptionPost adoptionPost, Tag tag) {
        tagAdoptionPostMappingRepository.save(
                TagAdoptionPostMapping.builder()
                        .adoptionPost(adoptionPost)
                        .tag(tag)
                        .build()
        );
    }

    @Transactional
    public void deleteMapping(TagAdoptionPostMapping tagAdoptionPostMapping) {
        tagAdoptionPostMappingRepository.delete(tagAdoptionPostMapping);
    }

    @Transactional
    public void deleteAllMapping(AdoptionPost adoptionPost) {
        List<TagAdoptionPostMapping> mappingList =  tagAdoptionPostMappingRepository.findMappingListByAdoptionPost(adoptionPost);
        mappingList
                .forEach(this::deleteMapping);
    }
}
