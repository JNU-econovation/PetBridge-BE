package PetBridge.animal.service;

import PetBridge.animal.exception.tagException.badRequestException.TagNotFoundException;
import PetBridge.animal.model.entity.Tag;
import PetBridge.animal.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public List<Tag> findTagListByAttributeOrThrow(String attribute) {
        return tagRepository.findByAttribute(attribute);
    }

    @Transactional(readOnly = true)
    public List<Tag> findByIdListOrThrow (List<Long> tagIdList) {
        return tagIdList
                .stream()
                .map(this::findByIdOrThrow)
                .toList();

    }

    @Transactional(readOnly = true)
    public Tag findByIdOrThrow(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(TagNotFoundException::new);
    }
}
