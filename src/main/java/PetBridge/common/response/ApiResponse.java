package PetBridge.common.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final boolean success;
    private final int code; // HTTP 상태 코드
    private final String message;
    private final T data;

    public ApiResponse(boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data, String message, int code) {
        return new ApiResponse<>(true, code, message, data);
    }

    public static ApiResponse<Void> success(String message, int code) {
        return new ApiResponse<>(true, code, message, null);
    }

    public static ApiResponse<Void> error(int code, String message) {
        return new ApiResponse<>(false, code, message, null);
    }
}
