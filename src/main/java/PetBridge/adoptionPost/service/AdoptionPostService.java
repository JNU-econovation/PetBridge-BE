package PetBridge.adoptionPost.service;

import PetBridge.adoptionPost.dto.AdoptionPostCreateDTO;
import PetBridge.adoptionPost.dto.AdoptionPostUpdateDTO;
import PetBridge.adoptionPost.exception.AdoptionPostNotFoundException;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.repository.AdoptionPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionPostService {
    private final AdoptionPostRepository adoptionPostRepository;

    public AdoptionPostService(AdoptionPostRepository adoptionPostRepository) {
        this.adoptionPostRepository = adoptionPostRepository;
    }

    public AdoptionPost findByIdOrThrow(Long postId) {
        return adoptionPostRepository.findById(postId)
                .orElseThrow(() -> new AdoptionPostNotFoundException("해당 ID의 분양글을 찾을 수 없습니다: " + postId));
    }

    //분양글 생성
    public void createAdoptionPost(AdoptionPostCreateDTO dto) {
        // DTO를 엔티티로 변환
        AdoptionPost post = dto.toEntity();
        adoptionPostRepository.save(post);
    }

    // 분양글 수정
    public AdoptionPost updateAdoptionPost(Long postId, AdoptionPostUpdateDTO adoptionPostUpdateDTO) {
        // 1. 기존 분양글 조회
        AdoptionPost existingPost = findByIdOrThrow(postId);

        // 2. 필드 업데이트
        return updateFields(existingPost, adoptionPostUpdateDTO);
    }

    // 필드 업데이트 메서드
    private AdoptionPost updateFields(AdoptionPost existingPost, AdoptionPostUpdateDTO dto) {
        return existingPost.toBuilder()
                .title(dto.getTitle())
                .subTitle(dto.getSubTitle())
                .weight(dto.getWeight())
                .age(dto.getAge())
                .isNeutered(dto.getIsNeutered())
                .isAdoptionContractRequired(dto.getIsAdoptionContractRequired())
                .meetingPlace(dto.getMeetingPlace())
                .likes(dto.getLikes())
                .hates(dto.getHates())
                .currentDiseases(dto.getCurrentDiseases())
                .pastDiseases(dto.getPastDiseases())
                .petOwnerRequirement(dto.getPetOwnerRequirement())
                .detailContent(dto.getDetailContent())
                .adoptionFinalizationStatus(dto.getAdoptionFinalizationStatus())
                .build();
    }

    //분양글 삭제
    public void deleteAdoptionPost(Long postId) {
        AdoptionPost adoptionPost = findByIdOrThrow(postId);
        adoptionPostRepository.delete(adoptionPost);
    }

    // 전체 분양글 조회
    public List<AdoptionPost> getAllAdoptionPosts() {
        return adoptionPostRepository.findAll(); // 모든 분양글 반환
    }

    // 특정 ID로 분양글 조회
    public AdoptionPost getAdoptionPostById(Long postid) {
        return findByIdOrThrow(postid);
    }

}
