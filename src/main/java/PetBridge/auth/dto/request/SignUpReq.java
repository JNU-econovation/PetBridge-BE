package PetBridge.auth.dto.request;

import PetBridge.member.model.entity.Member;

public record SignUpReq(
        String email,
        String password,
        String nickname
) {
    public Member toEntity () {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .searchHistoryCount(0L)
                .build();
    }
}
