package PetBridge.auth.service;

import PetBridge.auth.dto.AuthTokenDTO;
import PetBridge.auth.dto.AccessTokenDTO;
import PetBridge.auth.dto.request.LoginReq;
import PetBridge.auth.dto.request.SignUpReq;
import PetBridge.auth.jwt.service.AuthTokenService;
import PetBridge.member.model.entity.Member;
import PetBridge.member.service.MemberService;
import PetBridge.search.service.SearchConditionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthService {
    private final MemberService memberService;
    private final AuthTokenService authTokenService;
    private final SearchConditionService searchConditionService;

    @Transactional
    public void signUp(SignUpReq signUpRequest) {
        Member member = memberService.createMember(signUpRequest);
        searchConditionService.addSearchCondition(member);
    }

    @Transactional
        public AuthTokenDTO login(LoginReq loginReqDTO) {
            Member member = memberService.findByEmailAndPasswordOrThrow(loginReqDTO);
            searchConditionService.resetSearchCondition(member);
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
