package PetBridge.auth.jwt.service;

import PetBridge.auth.jwt.entity.RefreshToken;
import PetBridge.auth.jwt.exception.badRequestException.ExpiredTokenException;
import PetBridge.auth.jwt.exception.badRequestException.NotValidRefreshTokenException;
import PetBridge.auth.jwt.exception.badRequestException.NotValidTokenException;
import PetBridge.auth.jwt.exception.badRequestException.NullTokenException;
import PetBridge.auth.jwt.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProviderService {
    private final String BEARER = "Bearer ";

    private String secretKey;
    private Key key;
    private Long accessTokenExpiredTime;
    private Long refreshTokenExpiredTime;
    private final RefreshTokenRepository refreshTokenRepository;

    public JwtTokenProviderService(@Value("${jwt.secretKey}") String secretKey,
                                   @Value("${jwt.accessTokenExpiredTime}") Long accessTokenExpiredTime,
                                   @Value("${jwt.refreshTokenExpiredTime}") Long refreshTokenExpiredTime,
                                   RefreshTokenRepository refreshTokenRepository) {
        this.secretKey = secretKey;
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes((StandardCharsets.UTF_8)));
        this.accessTokenExpiredTime = accessTokenExpiredTime;
        this.refreshTokenExpiredTime = refreshTokenExpiredTime;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String generateAccessToken(Long memberId) {
        Date now = new Date();
        return Jwts.builder()
                .claim("id", memberId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenExpiredTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Transactional
    public String generateRefreshToken(Long memberId) {
        Date now = new Date();
        String refreshToken = Jwts.builder()
                .claim("id", memberId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenExpiredTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        RefreshToken token = new RefreshToken(memberId, refreshToken);
        refreshTokenRepository.save(token); //이미 해당 유저의 리프레쉬 토큰값이 존재하는 경우 insert 쿼리를 통해 리프레쉬 토큰을 업데이트

        return refreshToken;
    }

    public String extractToken(String token) {
        if (token == null)
            throw new NullTokenException();

        if (!token.contains(BEARER))
            throw new NotValidTokenException();

        return token.substring(BEARER.length());
    }

    public Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (SignatureException e) {
            throw new NotValidTokenException();
        }
    }

    @Transactional
    public void deleteByMemberIdOrThrow(Long memberId) {
        RefreshToken refreshToken = refreshTokenRepository.findById(memberId)
                .orElseThrow(NotValidRefreshTokenException::new);
        refreshTokenRepository.delete(refreshToken);
    }

    public boolean validationRefreshToken(String refreshToken) {
        Claims claims = parseClaims(refreshToken);
        Long memberId = claims.get("id", Long.class);
        return refreshTokenRepository.existsById(memberId);
    }
}
