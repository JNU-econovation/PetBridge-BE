package PetBridge.auth.jwt.annotation;

import PetBridge.auth.jwt.service.JwtTokenProviderService;
import PetBridge.member.service.MemberService;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class ValidMemberResolver implements HandlerMethodArgumentResolver {
    private final MemberService memberService;
    private final JwtTokenProviderService jwtTokenProviderService;

    public ValidMemberResolver(MemberService memberService, JwtTokenProviderService jwtTokenProviderService) {
        this.memberService = memberService;
        this.jwtTokenProviderService = jwtTokenProviderService;
    }


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ValidMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        String token = jwtTokenProviderService.extractToken(webRequest.getHeader(HttpHeaders.AUTHORIZATION)); //Http 헤더에서 토큰 추출
        Long memberId = jwtTokenProviderService.parseClaims(token).get("id", Long.class); //토큰에 들어있는 멤버Id값 추출
        return memberService.findByIdOrThrow(memberId);
    }
}
