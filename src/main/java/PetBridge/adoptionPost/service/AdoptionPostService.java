package PetBridge.adoptionPost.service;

import PetBridge.adoptionPost.dto.AdoptionPostDTO;
import PetBridge.adoptionPost.exception.AdoptionPostNotFoundException;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.repository.AdoptionPostRepository;
import PetBridge.adoptionPost.validator.AdoptionPostValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionPostService {
    private final AdoptionPostRepository adoptionPostRepository;
    private final AdoptionPostValidator adoptionPostValidator;

    public AdoptionPostService(AdoptionPostRepository adoptionPostRepository, AdoptionPostValidator adoptionPostValidator) {
        this.adoptionPostRepository = adoptionPostRepository;
        this.adoptionPostValidator = adoptionPostValidator;
    }

    //분양글 생성
    public void createAdoptionPost(AdoptionPost post) {
        if (post.getMember() == null || post.getBreed() == null || post.getTitle() == null) {
            throw new IllegalArgumentException("필수 정보가 누락되었습니다.");
        }
        adoptionPostRepository.save(post);
    }

    //분양글 수정
    public AdoptionPost updateAdoptionPost(Long id, AdoptionPostDTO adoptionPostDTO) {
        // 1. 기존 분양글 조회
        AdoptionPost existingPost = adoptionPostRepository.findById(id)
                .orElseThrow(() -> new AdoptionPostNotFoundException( "해당 ID의 분양글을 찾을 수 없습니다: " + id));

        //유효성 검사 호출
        adoptionPostValidator.validateUpdateDTO(adoptionPostDTO);

        // 2. 필드 업데이트
        AdoptionPost updatedPost = existingPost.toBuilder()
                .title(adoptionPostDTO.getTitle())
                .subTitle(adoptionPostDTO.getSubTitle())
                .weight(adoptionPostDTO.getWeight())
                .age(adoptionPostDTO.getAge())
                .isNeutered(adoptionPostDTO.getIsNeutered())
                .isAdoptionContractRequired(adoptionPostDTO.getIsAdoptionContractRequired())
                .meetingPlace(adoptionPostDTO.getMeetingPlace())
                .likes(adoptionPostDTO.getLikes())
                .hates(adoptionPostDTO.getHates())
                .currentDiseases(adoptionPostDTO.getCurrentDiseases())
                .pastDiseases(adoptionPostDTO.getPastDiseases())
                .petOwnerRequirement(adoptionPostDTO.getPetOwnerRequirement())
                .detailContent(adoptionPostDTO.getDetailContent())
                .adoptionFinalizationStatus(adoptionPostDTO.getAdoptionFinalizationStatus())
                .build();

        return updatedPost;
        //JPA는 변경사항이 있으면 그냥 자동으로 저장해줌 그래서 따로 save안해도 됨
        //return adoptionPostRepository.save(existingPost);
    }

    //분양글 삭제
    public void deleteAdoptionPost(Long id) {
        adoptionPostRepository.findById(id)
                .orElseThrow(()-> new AdoptionPostNotFoundException("해당 ID의 분양글을 찾을 수 없습니다: " + id));
        //fix: 커스텀예외도입이 필요함
        adoptionPostRepository.deleteById(id);
    }

    // 전체 분양글 조회
    public List<AdoptionPost> getAllAdoptionPosts() {
        return adoptionPostRepository.findAll(); // 모든 분양글 반환
    }

    // 특정 ID로 분양글 조회
    public AdoptionPost getAdoptionPostById(Long id) {
        return adoptionPostRepository.findById(id)
                .orElseThrow(() -> new AdoptionPostNotFoundException("해당 ID의 분양글을 찾을 수 없습니다: " + id));
    }


}
