package PetBridge.common.interceptor;

import PetBridge.auth.jwt.service.JwtTokenProviderService;
import PetBridge.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final String REISSUE_URI = "/api/v1/auth/reissue";

    private final JwtTokenProviderService jwtTokenProviderService;
    private final MemberService memberService;

    public JwtInterceptor(JwtTokenProviderService jwtTokenProviderService, MemberService memberService) {
        this.jwtTokenProviderService = jwtTokenProviderService;
        this.memberService = memberService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        if (StringUtils.equals(request.getMethod(), "OPTIONS")){
            return true;
        }

        String token = jwtTokenProviderService.extractToken(request.getHeader(HttpHeaders.AUTHORIZATION));

        if(request.getRequestURI().equals(REISSUE_URI)) //reissue 토큰인 경우 토큰 존재하는지 확인
            return jwtTokenProviderService.validationRefreshToken(token);

        Long memberId = jwtTokenProviderService.parseClaims(token).get("id", Long.class);
        memberService.findByIdOrThrow(memberId);//존재하는 멤버인지 확인

        return true;
    }

}
