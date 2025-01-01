package PetBridge.adoptionPost.service;

import PetBridge.adoptionPost.exception.AdoptionPostNotFoundException;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.repository.AdoptionPostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public AdoptionPost updateAdoptionPost(Long id, AdoptionPost updatedPost) {
        // 1. 기존 분양글 조회
        AdoptionPost existingPost = adoptionPostRepository.findById(id)
                .orElseThrow(() -> new AdoptionPostNotFoundException( "해당 ID의 분양글을 찾을 수 없습니다: " + id));

        // 2. 필드 업데이트
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setSubTitle(updatedPost.getSubTitle());
        existingPost.setWeight(updatedPost.getWeight());
        existingPost.setAge(updatedPost.getAge());
        existingPost.setIsNeutered(updatedPost.getIsNeutered());
        existingPost.setIsAdoptionContractRequired(updatedPost.getIsAdoptionContractRequired());
        existingPost.setMeetingPlace(updatedPost.getMeetingPlace());
        existingPost.setLikes(updatedPost.getLikes());
        existingPost.setHates(updatedPost.getHates());
        existingPost.setCurrentDiseases(updatedPost.getCurrentDiseases());
        existingPost.setPastDiseases(updatedPost.getPastDiseases());
        existingPost.setPetOwnerRequirement(updatedPost.getPetOwnerRequirement());
        existingPost.setDetailContent(updatedPost.getDetailContent());
        existingPost.setAdoptionFinalizationStatus(updatedPost.getAdoptionFinalizationStatus());

        /* updatedPost의 모든 필드를 existingPost로 복사
        BeanUtils.copyProperties(updatedPost, existingPost);*/

        return existingPost;
        //JPA는 변경사항이 있으면 그냥 자동으로 저장해줌 그래서 따로 save안해도 됨
        //return adoptionPostRepository.save(existingPost);
    }

    public void deleteAdoptionPost(Long id) {
        adoptionPostRepository.findById(id)
                .orElseThrow(()-> new AdoptionPostNotFoundException("해당 ID의 분양글을 찾을 수 없습니다: " + id));
        //fix: 커스텀예외도입이 필요함
        adoptionPostRepository.deleteById(id);
    }

    // 전체 조회
    public List<AdoptionPost> getAllAdoptionPosts() {
        return adoptionPostRepository.findAll(); // 모든 분양글 반환
    }

    // ID로 조회
    public AdoptionPost getAdoptionPostById(Long id) {
        return adoptionPostRepository.findById(id)
                .orElseThrow(() -> new AdoptionPostNotFoundException("해당 ID의 분양글을 찾을 수 없습니다: " + id));
    }


}
