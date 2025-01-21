package PetBridge.adoptionPost.service;

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
    private final TagAdoptionPostMappingRepository tagAdoptionPostMappingRepository;

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
