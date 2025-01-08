package PetBridge.auth.dto;

import PetBridge.auth.dto.response.LoginRes;

public record AuthTokenDTO(
        String accessToken,
        Long accessExpiredTime,
        String refreshToken
) {
    public LoginRes toLoginRes() {
        return new LoginRes(accessToken, accessExpiredTime, refreshToken);
    }
}
