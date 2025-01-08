package PetBridge.adoptionPost.exception;

public class AdoptionPostNotFoundException extends RuntimeException {
    public AdoptionPostNotFoundException(String message) {
        super(message);//RuntimeException에 메시지를 전달해 예외 메시지를 저장할 수 있도록 함
    }
}
