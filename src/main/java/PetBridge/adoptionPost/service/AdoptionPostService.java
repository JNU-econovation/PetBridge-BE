package PetBridge.adoptionPost.service;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.repository.AdoptionPostRepository;
import org.springframework.stereotype.Service;

@Service
public class AdoptionPostService {
    private final AdoptionPostRepository adoptionPostRepository;

    public AdoptionPostService(AdoptionPostRepository adoptionPostRepository) {
        this.adoptionPostRepository = adoptionPostRepository;
    }

    public void createAdoptionPost(AdoptionPost post) {
        if (post.getMember() == null || post.getBreed() == null || post.getTitle() == null) {
            throw new IllegalArgumentException("필수 정보가 누락되었습니다.");
        }
        adoptionPostRepository.save(post);

    }
}
