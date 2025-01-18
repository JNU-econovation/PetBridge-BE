package PetBridge.wish.controller;

import PetBridge.auth.jwt.annotation.ValidMember;
import PetBridge.member.model.entity.Member;
import PetBridge.wish.service.WishService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
