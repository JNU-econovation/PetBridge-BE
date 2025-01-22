package PetBridge.wish.controller;

import PetBridge.adoptionPost.dto.AdoptionPostSortDTO;
import PetBridge.auth.jwt.annotation.ValidMember;
import PetBridge.member.model.entity.Member;
import PetBridge.wish.dto.res.GetWishPostListRes;
import PetBridge.wish.service.WishService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wish")
@AllArgsConstructor
public class WishController {
    private WishService wishService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> addWish (
            @ValidMember Member member,
            @PathVariable("postId") Long postId
    ) {
        wishService.addWish(member, postId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteWish(
            @ValidMember Member member,
            @PathVariable("postId") Long postId
    ) {
        wishService.deleteWish(member, postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/list")
    public ResponseEntity<GetWishPostListRes> getWishPostList(
            @ValidMember Member member
    ) {
        List<AdoptionPostSortDTO> wishPostList = wishService.getWishPostList(member);
        return ResponseEntity.status(HttpStatus.OK).body(new GetWishPostListRes(wishPostList));
    }
}
