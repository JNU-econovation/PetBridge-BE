package PetBridge.common.exception.serverException;

import PetBridge.common.exception.CommonErrorType;
import PetBridge.common.exception.InternalServerException;

public class S3Exception extends InternalServerException {
    public S3Exception() {
        super(CommonErrorType.S3_IO_EXCEPTION);
    }
}
