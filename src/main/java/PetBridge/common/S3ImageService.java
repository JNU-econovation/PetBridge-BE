package PetBridge.common;

import PetBridge.common.exception.serverException.S3Exception;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3ImageService {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucket;

    public String saveImage(MultipartFile image) {
        String fileName = UUID.randomUUID() + image.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(image.getContentType());
        metadata.setContentLength(image.getSize());
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket,fileName,image.getInputStream(),metadata );
            amazonS3Client.putObject(putObjectRequest);
        } catch (IOException e) {
            throw new S3Exception();
        }
        return amazonS3Client.getUrl(bucket,fileName).toString();
    }

    public void deleteImage(String fileName) {
        try {
            amazonS3Client.deleteObject(bucket,fileName);
        } catch (SdkClientException e) {
            throw new S3Exception();
        }
    }
}
