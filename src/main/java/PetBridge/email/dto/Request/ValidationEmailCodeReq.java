package PetBridge.email.dto.Request;

public record ValidationEmailCodeReq (
        String email,
        String emailCode
){
}
