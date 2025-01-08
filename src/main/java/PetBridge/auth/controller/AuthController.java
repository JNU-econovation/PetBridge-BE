package PetBridge.auth.controller;

import PetBridge.auth.dto.AuthTokenDTO;
import PetBridge.auth.dto.AccessTokenDTO;
import PetBridge.auth.dto.request.LoginReq;
import PetBridge.auth.dto.request.ReissueReq;
import PetBridge.auth.dto.request.SignUpReq;
import PetBridge.auth.dto.response.LoginRes;
import PetBridge.auth.dto.response.ReissueRes;
import PetBridge.auth.jwt.annotation.ValidMember;
import PetBridge.auth.service.AuthService;
import PetBridge.member.model.entity.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup (
            @RequestBody SignUpReq signUpRequest
    ) {
        authService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login (
            @RequestBody LoginReq loginReqDTO
    ) {
        AuthTokenDTO authTokenDTO = authService.login(loginReqDTO);
        return ResponseEntity.status(HttpStatus.OK).body(authTokenDTO.toLoginRes());
    }

    @PostMapping("/reissue")
    public ResponseEntity<ReissueRes> reissue (
            @ValidMember Member member,
            @RequestBody ReissueReq reissueReq
    ) {
        AccessTokenDTO accessTokenDTO = authService.reissue(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(accessTokenDTO.toReissueRes());
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout (
            @ValidMember Member member
    ) {
        authService.logout(member);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
