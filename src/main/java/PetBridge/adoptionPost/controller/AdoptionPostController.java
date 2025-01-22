package PetBridge.adoptionPost.controller;

import PetBridge.adoptionPost.dto.*;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.service.AdoptionPostSearchService;
import PetBridge.adoptionPost.service.AdoptionPostService;
import PetBridge.auth.jwt.annotation.ValidMember;
import PetBridge.member.model.entity.Member;
import PetBridge.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/adoption-post")
public class AdoptionPostController {
    private static final String SORT_DEFAULT = "recent";

    private final AdoptionPostService adoptionPostService;
    private final MemberService memberService;
    private final AdoptionPostSearchService adoptionPostSearchService;

    // 분양글 생성
    @PostMapping
    public ResponseEntity<Void> createAdoptionPost(
            @RequestBody @Valid AdoptionPostCreateDTO adoptionPostCreateDTO,
            @ValidMember Member member) {
        adoptionPostService.createAdoptionPost(adoptionPostCreateDTO, member);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //분양글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<Void> updateAdoptionPost(
            @PathVariable("postId") Long postId,
            @RequestBody @Valid AdoptionPostUpdateDTO adoptionPostUpdateDTO,
            @ValidMember Member member
    ) {
        adoptionPostService.updateAdoptionPost(postId, adoptionPostUpdateDTO, member);
        return ResponseEntity.ok().build();
    }

    //분양글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteAdoptionPost(
            @PathVariable Long postId,
            @ValidMember Member member) {
        adoptionPostService.deleteAdoptionPost(postId, member);
        return ResponseEntity.noContent().build(); // 204 No Content 반환
    }

    // ID로 특정 분양글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<AdoptionPostDetailDTO> getAdoptionPostById(
            @PathVariable Long postId,
            @ValidMember Member member) {
        AdoptionPostDetailDTO dto = adoptionPostService.getAdoptionPostById(postId);
        return ResponseEntity.ok(dto); // 200 OK와 함께 해당 객체 반환
    }


    // 분양글 조회 (전체 조회 및 정렬 조회) + 검색 기록 저장 기능
    @GetMapping
    public ResponseEntity<List<AdoptionPostSortDTO>> searchAdoptionPosts(
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "tagIdList", required = false) List<Long> tagIdList,
            @RequestParam(name = "breedIdList", required = false) List<Long> breedIdList,
            @ValidMember Member member) {

        List<AdoptionPostSortDTO> searchedAdoptionPosts = adoptionPostSearchService.getAdoptionPosts(sortBy, member, keyword, tagIdList, breedIdList);
        return ResponseEntity.status(HttpStatus.OK).body(searchedAdoptionPosts);
    }

    @GetMapping("/my-posts")
    public ResponseEntity<GetMyPostListRes> getMyPostList(
            @ValidMember Member member
    ) {
        List<AdoptionPostDTO> adoptionPostList= adoptionPostService.findListByMember(member);
        return ResponseEntity.status(HttpStatus.OK).body(new GetMyPostListRes(adoptionPostList));
    }
}