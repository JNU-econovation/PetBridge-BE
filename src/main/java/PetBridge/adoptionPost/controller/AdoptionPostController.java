package PetBridge.adoptionPost.controller;

import PetBridge.adoptionPost.dto.AdoptionPostCreateDTO;
import PetBridge.adoptionPost.dto.AdoptionPostDetailDTO;
import PetBridge.adoptionPost.dto.AdoptionPostSortDTO;
import PetBridge.adoptionPost.dto.AdoptionPostUpdateDTO;
import PetBridge.adoptionPost.service.AdoptionPostService;
import PetBridge.common.response.ApiResponse;
import PetBridge.member.model.entity.Member;
import PetBridge.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/adoption-post")
public class AdoptionPostController {
    private final AdoptionPostService service;
    private final MemberService memberService;

    public AdoptionPostController(AdoptionPostService service, MemberService memberService) {
        this.service = service;
        this.memberService = memberService;
    }

    // 분양글 생성
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createAdoptionPost(
            @RequestBody @Valid AdoptionPostCreateDTO adoptionPostCreateDTO) {
        service.createAdoptionPost(adoptionPostCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(ApiResponse.success("분양글이 성공적으로 생성되었습니다.",HttpStatus.CREATED.value()));
    }

    //분양글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> updateAdoptionPost(
            @PathVariable("postId") Long postId,
            @RequestBody @Valid AdoptionPostUpdateDTO adoptionPostUpdateDTO) {
        service.updateAdoptionPost(postId, adoptionPostUpdateDTO);
        return ResponseEntity.ok(ApiResponse.success(String.format("분양글 ID:%d가 성공적으로 수정되었습니다.", postId), HttpStatus.OK.value()));
    }

    //분양글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> deleteAdoptionPost(
            @PathVariable Long postId) {
        service.deleteAdoptionPost(postId);
        return ResponseEntity.ok(ApiResponse.success(null, String.format("분양글 ID:%d가 성공적으로 삭제되었습니다.", postId), HttpStatus.NO_CONTENT.value()));
    }

    // ID로 특정 분양글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<AdoptionPostDetailDTO>> getAdoptionPostById(
            @PathVariable Long postId) {
        AdoptionPostDetailDTO dto = service.getAdoptionPostById(postId);
        return ResponseEntity.ok(ApiResponse.success(dto, String.format("분양글 ID:%d가 성공적으로 조회되었습니다.", postId), HttpStatus.OK.value())); // 200 OK와 함께 해당 객체 반환
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
        return ResponseEntity.ok(ApiResponse.success(posts, "분양글이 성공적으로 정렬되었습니다.", HttpStatus.OK.value()));
    }

}