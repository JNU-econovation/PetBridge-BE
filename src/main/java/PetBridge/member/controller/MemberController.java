package PetBridge.member.controller;

import PetBridge.auth.jwt.annotation.ValidMember;
import PetBridge.member.model.entity.Member;
import PetBridge.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/nickname/{value}")
    public ResponseEntity<Void> validateDuplicatedNickname (
            @PathVariable("value") String nickname
    ) {
        memberService.validateDuplicateNickname(nickname);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/nickname")
    public ResponseEntity<Void> changeNickname (
            @ValidMember Member member,
            @RequestParam ("value") String nickname
    ) {
        memberService.changeNickname(member, nickname);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
