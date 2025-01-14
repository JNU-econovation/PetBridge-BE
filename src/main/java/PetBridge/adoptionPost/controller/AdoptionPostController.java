package PetBridge.adoptionPost.controller;

import PetBridge.adoptionPost.dto.AdoptionPostCreateDTO;
import PetBridge.adoptionPost.dto.AdoptionPostDetailDTO;
import PetBridge.adoptionPost.dto.AdoptionPostSortDTO;
import PetBridge.adoptionPost.dto.AdoptionPostUpdateDTO;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.service.AdoptionPostService;
import PetBridge.member.model.entity.Member;
import PetBridge.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Void> createAdoptionPost(
            @RequestBody @Valid AdoptionPostCreateDTO adoptionPostCreateDTO) {
        service.createAdoptionPost(adoptionPostCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //분양글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<Void> updateAdoptionPost(
            @PathVariable("postId") Long postId,
            @RequestBody @Valid AdoptionPostUpdateDTO adoptionPostUpdateDTO) {
        AdoptionPost post = service.updateAdoptionPost(postId, adoptionPostUpdateDTO);
        return ResponseEntity.ok().build();
    }

    //분양글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteAdoptionPost(
            @PathVariable Long postId) {
        service.deleteAdoptionPost(postId);
        return ResponseEntity.noContent().build(); // 204 No Content 반환
    }

    // ID로 특정 분양글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<AdoptionPostDetailDTO> getAdoptionPostById(
            @PathVariable Long postId) {
        AdoptionPostDetailDTO dto = service.getAdoptionPostById(postId);
        return ResponseEntity.ok(dto); // 200 OK와 함께 해당 객체 반환
    }

    // 분양글 조회 (전체 조회 및 정렬 조회) + 검색 기록 저장 기능
    @GetMapping
    public Slice<AdoptionPostSortDTO> getAdoptionPosts(
            @RequestParam(required = false, defaultValue = "all") String sortBy,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = true) Long memberId,
            Pageable pageable) {
        Member member = memberService.findByIdOrThrow(memberId);
        return service.getAdoptionPosts(sortBy, pageable, member, keyword);
    }

}