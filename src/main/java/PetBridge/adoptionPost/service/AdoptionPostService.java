package PetBridge.adoptionPost.service;

import PetBridge.adoptionPost.dto.AdoptionPostCreateDTO;
import PetBridge.adoptionPost.dto.AdoptionPostDetailDTO;
import PetBridge.adoptionPost.dto.AdoptionPostUpdateDTO;
import PetBridge.adoptionPost.exception.AdoptionPostNotFoundException;
import PetBridge.adoptionPost.mapper.AdoptionPostMapper;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.repository.AdoptionPostRepository;
import PetBridge.animal.model.entity.Breed;
import PetBridge.animal.model.entity.Tag;
import PetBridge.animal.service.BreedService;
import PetBridge.animal.service.TagService;
import PetBridge.member.model.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AdoptionPostService {

    private final AdoptionPostRepository adoptionPostRepository;
    private final BreedService breedService;
    private final TagService tagService;
    private final TagAdoptionPostMappingService tagAdoptionPostMappingService;
    private final AdoptionPostMapper adoptionPostMapper;

    @Transactional(readOnly = true)
    public AdoptionPost findByIdOrThrow(Long postId) {
        return adoptionPostRepository.findById(postId)
                .orElseThrow(AdoptionPostNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public AdoptionPost findByIdAndMemberOrThrow(Long postId, Member member) {
        return adoptionPostRepository.findByIdAndMember(postId, member)
                .orElseThrow(AdoptionPostNotFoundException::new);
    }


    //분양글 생성
    @Transactional
    public void createAdoptionPost(AdoptionPostCreateDTO dto, Member member) {
        Breed breed = breedService.findByIdOrThrow(dto.breedId());
        List<Tag> tagList = tagService.findByIdListOrThrow(dto.tagIdList());

        AdoptionPost post = dto.toEntity(member,breed);
        adoptionPostRepository.save(post);

        tagList.forEach(tag -> tagAdoptionPostMappingService.saveMapping(post, tag));
    }

    // 분양글 수정
    @Transactional
    public AdoptionPost updateAdoptionPost(Long postId, AdoptionPostUpdateDTO adoptionPostUpdateDTO, Member member) {
        AdoptionPost adoptionPost = findByIdOrThrow(postId);

        return updateFields(adoptionPost, adoptionPostUpdateDTO);
    }

    private AdoptionPost updateFields(AdoptionPost adoptionPost, AdoptionPostUpdateDTO dto) {
        return adoptionPost.toBuilder()
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
    public void deleteAdoptionPost(Long postId, Member member) {
        AdoptionPost adoptionPost = findByIdAndMemberOrThrow(postId,member);
        adoptionPostRepository.delete(adoptionPost);
        tagAdoptionPostMappingService.deleteAllMapping(adoptionPost);
    }

    // 전체 분양글 조회
    @Transactional(readOnly = true)
    public List<AdoptionPost> getAllAdoptionPosts() {
        return adoptionPostRepository.findAll(); // 모든 분양글 반환
    }

    // 특정 ID로 분양글 조회 (분양글 상세페이지)
    @Transactional(readOnly = true)
    public AdoptionPostDetailDTO getAdoptionPostById(Long postId) {
        AdoptionPost adoptionPost = findByIdOrThrow(postId);
        Tag genderTag = tagAdoptionPostMappingService.findGenderTagOfAdoptionPost(adoptionPost);
        List<Tag> inoculationTagList = tagAdoptionPostMappingService.findInoculationTagListOfAdoptionPost(adoptionPost);

        return adoptionPostMapper.toAdoptionPostDetailDTO(adoptionPost,genderTag.getName(), inoculationTagList);
    }

    @Transactional
    public void increaseWishCountById(Long postId) {
        AdoptionPost adoptionPost = findByIdOrThrow(postId);
        adoptionPostRepository.incrementWishCount(adoptionPost);
    }

    @Transactional
    public void decreaseWishCountById(Long postId) {
        AdoptionPost adoptionPost = findByIdOrThrow(postId);
        adoptionPostRepository.decrementWishCount(adoptionPost);
    }

    public void increaseWishCount(AdoptionPost adoptionPost) {
    }
}