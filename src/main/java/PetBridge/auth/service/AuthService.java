package PetBridge.auth.service;

import PetBridge.auth.dto.AuthTokenDTO;
import PetBridge.auth.dto.AccessTokenDTO;
import PetBridge.auth.dto.request.LoginReq;
import PetBridge.auth.dto.request.SignUpReq;
import PetBridge.auth.jwt.service.AuthTokenService;
import PetBridge.member.model.entity.Member;
import PetBridge.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final MemberService memberService;
    private final AuthTokenService authTokenService;

    public AuthService(MemberService memberService, AuthTokenService authTokenService) {
        this.memberService = memberService;
        this.authTokenService = authTokenService;
    }

    @Transactional
    public void signUp(SignUpReq signUpRequest) {
        memberService.createMember(signUpRequest);
    }

    @Transactional
    public AuthTokenDTO login(LoginReq loginReqDTO) {
        Member member = memberService.findByEmailAndPasswordOrThrow(loginReqDTO);
        return authTokenService.generateAuthToken(member.getId());
    }

    @Transactional
    public AccessTokenDTO reissue(Member member) {
        return authTokenService.reissue(member.getId());
    }

    @Transactional
    public void logout(Member member) {
        authTokenService.logOut(member.getId());
    }
}
