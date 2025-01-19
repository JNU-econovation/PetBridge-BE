package PetBridge.adoptionPost.controller;

import PetBridge.adoptionPost.dto.AdoptionPostCreateDTO;
import PetBridge.adoptionPost.dto.AdoptionPostDetailDTO;
import PetBridge.adoptionPost.dto.AdoptionPostSortDTO;
import PetBridge.adoptionPost.dto.AdoptionPostUpdateDTO;
import PetBridge.adoptionPost.exception.AdoptionPostErrorType;
import PetBridge.adoptionPost.service.AdoptionPostService;
import PetBridge.adoptionPost.service.S3ImageService;
import PetBridge.common.response.ApiResponse;
import PetBridge.member.model.entity.Member;
import PetBridge.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/adoption-post")
public class AdoptionPostController {
    private final AdoptionPostService service;
    private final MemberService memberService;
    private final S3ImageService s3ImageService;

    public AdoptionPostController(AdoptionPostService service, MemberService memberService, S3ImageService s3ImageService) {
        this.service = service;
        this.memberService = memberService;
        this.s3ImageService = s3ImageService;
    }

    // 분양글 생성
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createAdoptionPost(
            @RequestBody @Valid AdoptionPostCreateDTO adoptionPostCreateDTO) {
        service.createAdoptionPost(adoptionPostCreateDTO);
        return ResponseEntity.status(AdoptionPostErrorType.POST_CREATED.getHttpStatus())
                .body(ApiResponse.success(
                        null,
                        AdoptionPostErrorType.POST_CREATED.getMessage(),
                        AdoptionPostErrorType.POST_CREATED.getHttpStatus().value()
                ));
    }

    //분양글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> updateAdoptionPost(
            @PathVariable("postId") Long postId,
            @RequestBody @Valid AdoptionPostUpdateDTO adoptionPostUpdateDTO) {
        service.updateAdoptionPost(postId, adoptionPostUpdateDTO);
        return ResponseEntity.ok(ApiResponse.success(
                null,
                AdoptionPostErrorType.POST_UPDATED.getMessage(),
                AdoptionPostErrorType.POST_UPDATED.getHttpStatus().value()
        ));
    }

    //분양글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> deleteAdoptionPost(
            @PathVariable Long postId) {
        service.deleteAdoptionPost(postId);
        return ResponseEntity.ok(ApiResponse.success(
                null,
                AdoptionPostErrorType.POST_DELETED.getMessage(),
                AdoptionPostErrorType.POST_DELETED.getHttpStatus().value()
        ));
    }

    // ID로 특정 분양글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<AdoptionPostDetailDTO>> getAdoptionPostById(
            @PathVariable Long postId) {
        AdoptionPostDetailDTO dto = service.getAdoptionPostById(postId);
        return ResponseEntity.ok(ApiResponse.success(
                dto,
                AdoptionPostErrorType.POST_FOUND.getMessage(),
                AdoptionPostErrorType.POST_FOUND.getHttpStatus().value()
        ));
    }

    // 분양글 조회 (전체 조회 및 정렬 조회) + 검색 기록 저장 기능
    @GetMapping
    public ResponseEntity<ApiResponse<Slice<AdoptionPostSortDTO>>> getAdoptionPosts(
            @RequestParam(required = false, defaultValue = "all") String sortBy,
            @RequestParam(required = false) String keyword,
            @RequestParam Long memberId,
            Pageable pageable) {
        Member member = memberService.findByIdOrThrow(memberId);
        Slice<AdoptionPostSortDTO> posts = service.getAdoptionPosts(sortBy, pageable, member, keyword);
        return ResponseEntity.ok(ApiResponse.success(
                posts,
                AdoptionPostErrorType.POST_SORTED.getMessage(),
                AdoptionPostErrorType.POST_SORTED.getHttpStatus().value()
        ));
    }

    @PostMapping("/images")
    public ResponseEntity<String> uploadImage(@RequestPart(value = "image") MultipartFile image) {
        try {
            //S3에 이미지를 업로드하고 업로드된 이미지 URL을 반환받음
            String imageUrl = s3ImageService.upload(image);
            //성공시 URL을 클라이언트에 반환함
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            //업로드 실패시 500 상태와 에러 메시지를 반환함
            return ResponseEntity.status(500).body("이미지 업로드 실패" + e.getMessage());
        }
    }

    @DeleteMapping("/images")
    public ResponseEntity<String> deleteImage(@RequestParam String imageUrl) {
        s3ImageService.delete(imageUrl);
        return ResponseEntity.ok("이미지 삭제 성공");
    }

}