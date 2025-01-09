package PetBridge.adoptionPost.service;

import PetBridge.adoptionPost.dto.AdoptionPostCreateDTO;
import PetBridge.adoptionPost.dto.AdoptionPostSortDTO;
import PetBridge.adoptionPost.dto.AdoptionPostUpdateDTO;
import PetBridge.adoptionPost.exception.AdoptionPostNotFoundException;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.repository.AdoptionPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdoptionPostService {
    private final AdoptionPostRepository adoptionPostRepository;

    public AdoptionPostService(AdoptionPostRepository adoptionPostRepository) {
        this.adoptionPostRepository = adoptionPostRepository;
    }
    
    //Transactional는 데이터 변경 작업(삽입,수정,삭제)을 포함 Transactional(readOnly=true) 는 데이터 조회 작업만 수행
    @Transactional(readOnly = true)
    public AdoptionPost findByIdOrThrow(Long postId) {
        return adoptionPostRepository.findById(postId)
                .orElseThrow(() -> new AdoptionPostNotFoundException("해당 ID의 분양글을 찾을 수 없습니다: " + postId));
    }

    //분양글 생성
    @Transactional
    public void createAdoptionPost(AdoptionPostCreateDTO dto) {
        // DTO를 엔티티로 변환
        AdoptionPost post = dto.toEntity();
        adoptionPostRepository.save(post);
    }

    // 분양글 수정
    @Transactional
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
    @Transactional
    public void deleteAdoptionPost(Long postId) {
        AdoptionPost adoptionPost = findByIdOrThrow(postId);
        adoptionPostRepository.delete(adoptionPost);
    }

    // 전체 분양글 조회
    @Transactional(readOnly = true)
    public List<AdoptionPost> getAllAdoptionPosts() {
        return adoptionPostRepository.findAll(); // 모든 분양글 반환
    }

    // 특정 ID로 분양글 조회
    @Transactional(readOnly = true)
    public AdoptionPost getAdoptionPostById(Long postId) {
        return findByIdOrThrow(postId);
    }

    // 정렬 기준에 따라 데이터 조회
    //현재 Repository 메서드가 AdoptionPost 엔티티 리스트를 반환하기 때문에 posts를 AdoptionPost엔티티로 선언함.
    @Transactional(readOnly = true)
    public List<AdoptionPostSortDTO> getAdoptionPosts(String sortBy) {
        List<AdoptionPost> posts;

        if ("recent".equals(sortBy)) { // 최신순
            posts = adoptionPostRepository.findAllByOrderByIdDescending();
        } else if ("view".equals(sortBy)) { // 조회순
            posts = adoptionPostRepository.findAllByOrderByClickCountDescending();
        } else if ("wish".equals(sortBy)) { // 찜순
            posts = adoptionPostRepository.findAllByOrderByWishCountDescending();
        } else if ("all".equals(sortBy)) { // 전체 조회
            posts = adoptionPostRepository.findAll();
        } else {
            throw new IllegalArgumentException("유효하지 않은 정렬 기준입니다.");
        }
        // DTO로 변환
        return posts.stream()
                .map(post -> new AdoptionPostSortDTO(
                        post.getId(),
                        post.getTitle(),
                        post.getSubTitle(),
                        post.getClickCount(),
                        post.getWishCount()
                ))
                .toList();
    }
}