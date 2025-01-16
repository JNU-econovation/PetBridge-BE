package PetBridge.adoptionPost.service;

import PetBridge.adoptionPost.dto.AdoptionPostCreateDTO;
import PetBridge.adoptionPost.dto.AdoptionPostDetailDTO;
import PetBridge.adoptionPost.dto.AdoptionPostSortDTO;
import PetBridge.adoptionPost.dto.AdoptionPostUpdateDTO;
import PetBridge.adoptionPost.exception.AdoptionPostNotFoundException;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.repository.AdoptionPostRepository;
import PetBridge.member.model.entity.Member;
import PetBridge.search.service.SearchService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdoptionPostService {

    private final AdoptionPostRepository adoptionPostRepository;
    private final SearchService searchService;

    public AdoptionPostService(AdoptionPostRepository adoptionPostRepository, SearchService searchService) {
        this.adoptionPostRepository = adoptionPostRepository;
        this.searchService = searchService;
    }

    //Transactional는 데이터 변경 작업(삽입,수정,삭제)을 포함 Transactional(readOnly=true) 는 데이터 조회 작업만 수행
    @Transactional(readOnly = true)
    public AdoptionPost findByIdOrThrow(Long postId) {
        return adoptionPostRepository.findById(postId)
                .orElseThrow(() -> new AdoptionPostNotFoundException());
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
    // updateFields는 엔티티의 필드 값만 변경하는 비즈니스 로직에 해당하며 데이터베이스와의 직접적인 상호작용이 없습니다.
    // 반면 findByIdOrThrow는 데이터베이스 조회 작업을 수행하므로 트랜잭션 관리가 필요합니다.
    private AdoptionPost updateFields(AdoptionPost existingPost, AdoptionPostUpdateDTO dto) {
        return existingPost.toBuilder()
                .title(dto.title()) // dto.getTitle() -> dto.title()
                .subTitle(dto.subTitle())
                .weight(dto.weight())
                .age(dto.age())
                .isNeutered(dto.isNeutered())
                .isAdoptionContractRequired(dto.isAdoptionContractRequired())
                .meetingPlace(dto.meetingPlace())
                .likes(dto.likes())
                .hates(dto.hates())
                .currentDiseases(dto.currentDiseases())
                .pastDiseases(dto.pastDiseases())
                .petOwnerRequirement(dto.petOwnerRequirement())
                .detailContent(dto.detailContent())
                .adoptionFinalizationStatus(dto.adoptionFinalizationStatus())
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

    // 특정 ID로 분양글 조회 (분양글 상세페이지)
    @Transactional(readOnly = true)
    public AdoptionPostDetailDTO getAdoptionPostById(Long postId) {
        AdoptionPost post = findByIdOrThrow(postId);

        return new AdoptionPostDetailDTO(post.getId(),
                post.getTitle(),
                post.getSubTitle(),
                post.getWeight(),
                post.getAge(),
                post.getIsNeutered(),
                post.getIsAdoptionContractRequired(),
                post.getMeetingPlace(),
                post.getLikes(),
                post.getHates(),
                post.getCurrentDiseases(),
                post.getPastDiseases(),
                post.getPetOwnerRequirement(),
                post.getDetailContent(),
                post.getAdoptionFinalizationStatus(),
                post.getClickCount(),
                post.getWishCount(),
                post.getBreed().getName(),
                post.getMember().getNickname()
        );
    }

    // 정렬 기준에 따라 데이터 조회
    //현재 Repository 메서드가 AdoptionPost 엔티티 리스트를 반환하기 때문에 posts를 AdoptionPost엔티티로 선언함.
    @Transactional(readOnly = true)
    public Slice<AdoptionPostSortDTO> getAdoptionPosts(String sortBy, Pageable pageable, Member member, String keyword) {

        // 키워드가 있는 경우에만 검색 기록 저장
        if (keyword != null && !keyword.trim().isEmpty()) {
            searchService.addSearchHistory(member, keyword);
        }

        Slice<AdoptionPost> posts;

        if ("recent".equals(sortBy)) { // 최신순
            posts = adoptionPostRepository.findAllByOrderByIdDesc(pageable);
        } else if ("view".equals(sortBy)) { // 조회순
            posts = adoptionPostRepository.findAllByOrderByClickCountDesc(pageable);
        } else if ("wish".equals(sortBy)) { // 찜순
            posts = adoptionPostRepository.findAllByOrderByWishCountDesc(pageable);
        } else if ("all".equals(sortBy)) { // 전체 조회
            posts = adoptionPostRepository.findAll(pageable);
        } else {
            throw new IllegalArgumentException("유효하지 않은 정렬 기준입니다.");
        }
        // DTO로 변환
        return posts.map(post -> new AdoptionPostSortDTO(
                post.getId(),
                post.getTitle(),
                post.getSubTitle(),
                post.getClickCount(),
                post.getWishCount()
        ));

    }
}