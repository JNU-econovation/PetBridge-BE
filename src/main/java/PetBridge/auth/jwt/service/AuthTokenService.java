package PetBridge.auth.jwt.service;

import PetBridge.auth.dto.AuthTokenDTO;
import PetBridge.auth.dto.AccessTokenDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AuthTokenService {
    private final JwtTokenProviderService jwtTokenProviderService;
    public AuthTokenService(JwtTokenProviderService jwtTokenProviderService) {
        this.jwtTokenProviderService = jwtTokenProviderService;
    }

    @Transactional
    public AuthTokenDTO generateAuthToken(Long memberId) {
        String accessToken = jwtTokenProviderService.generateAccessToken(memberId);
        String refreshToken = jwtTokenProviderService.generateRefreshToken(memberId);
        Long accessExpiredTime = getExpiredDateFromAccessToken(accessToken);
        return new AuthTokenDTO(accessToken,accessExpiredTime,refreshToken);
    }

    private Long getExpiredDateFromAccessToken (String accessToken) {
        return jwtTokenProviderService.parseClaims(accessToken).getExpiration().getTime();
    }

    @Transactional
    public AccessTokenDTO reissue (Long memberId) {
        String accessToken = jwtTokenProviderService.generateAccessToken(memberId);
        Long accessExpiredTime = getExpiredDateFromAccessToken(accessToken);
        return new AccessTokenDTO(accessToken, accessExpiredTime);
    }

    @Transactional
    public void logOut (Long memberId) {
        jwtTokenProviderService.deleteByMemberIdOrThrow(memberId);
    }
}
