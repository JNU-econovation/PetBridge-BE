package PetBridge.member.controller;

import PetBridge.auth.jwt.annotation.ValidMember;
import PetBridge.member.model.entity.Member;
import PetBridge.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
