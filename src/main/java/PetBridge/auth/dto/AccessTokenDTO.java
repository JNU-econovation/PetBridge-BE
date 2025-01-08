package PetBridge.auth.dto;

import PetBridge.auth.dto.response.ReissueRes;

public record AccessTokenDTO(
        String accessToken,
        Long accessExpiredTime
) {
    public ReissueRes toReissueRes() {
        return new ReissueRes(
                accessToken,
                accessExpiredTime
        );
    }
}
