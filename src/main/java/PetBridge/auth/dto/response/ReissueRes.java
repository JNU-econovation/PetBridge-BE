package PetBridge.auth.dto.response;

public record ReissueRes(
        String accessToken,
        Long accessExpiredTime
){
}
