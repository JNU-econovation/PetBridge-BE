package PetBridge.auth.dto.response;

public record LoginRes(
        String accessToken,
        Long accessExpiredTime,
        String refreshToken
) {
}
