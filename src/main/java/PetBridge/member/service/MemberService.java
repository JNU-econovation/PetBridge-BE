package PetBridge.member.service;

import PetBridge.auth.dto.request.LoginReq;
import PetBridge.auth.dto.request.SignUpReq;
import PetBridge.auth.exception.businessException.AlreadySignedEmailException;
import PetBridge.member.exception.AlreadyExistNicknameException;
import PetBridge.member.exception.BadRequest.MemberNotFoundException;
import PetBridge.member.exception.BadRequest.InvalidEmailOrPasswordException;
import PetBridge.member.model.entity.Member;
import PetBridge.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member findByIdOrThrow(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public void validateDuplicateEmail(String email) {
        Boolean existByEmail = memberRepository.existsByEmail(email);
        if (existByEmail)
            throw new AlreadySignedEmailException();
    }

    @Transactional(readOnly = true)
    public void validateDuplicateNickname(String nickname) {
        Boolean existByNickname = memberRepository.existsByNickname(nickname);
        if (existByNickname)
            throw new AlreadyExistNicknameException();
    }

    @Transactional(readOnly = true)
    public Member findByEmailAndPasswordOrThrow(LoginReq loginReqDTO) {
        return memberRepository.findByEmailAndPassword(loginReqDTO.email(), loginReqDTO.password())
                .orElseThrow(InvalidEmailOrPasswordException::new);
    }

    @Transactional
    public void createMember(SignUpReq signUpRequest) {
        validateDuplicateEmail(signUpRequest.email());
        memberRepository.save(signUpRequest.toEntity());
    }

    @Transactional
    public void changeNickname(Member member, String nickname) {
        member.changeNickname(nickname);
    }
}
