package PetBridge.auth.dto.request;

public record LoginReq(
        String email,
        String password
) {
}
