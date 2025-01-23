package PetBridge.adoptionPost.service;

import PetBridge.adoptionPost.exception.AdoptionPostImageNotFoundException;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.model.entity.AdoptionPostImage;
import PetBridge.adoptionPost.repository.AdoptionPostImageRepository;
import PetBridge.common.S3ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AdoptionPostImageService {
    private final AdoptionPostImageRepository adoptionPostImageRepository;
    private final S3ImageService s3ImageService;

    @Transactional
    public void addAdoptionPostImage(AdoptionPost post) {
        adoptionPostImageRepository.save(
                AdoptionPostImage.builder()
                        .imageUrlList(new ArrayList<>())
                        .mainImageUrl(null)
                        .adoptionPost(post)
                        .build()
        );
    }

    @Transactional
    public void saveImage(AdoptionPost adoptionPost, MultipartFile image) {
        String imageUrl = s3ImageService.saveImage(image);
        AdoptionPostImage adoptionPostImage = adoptionPostImageRepository.findByAdoptionPost(adoptionPost)
                .orElseThrow(AdoptionPostImageNotFoundException::new);

        adoptionPostImage.addImageUrl(imageUrl);
    }

    @Transactional
    public void saveMainImage(AdoptionPost adoptionPost, MultipartFile image) {
        String imageUrl = s3ImageService.saveImage(image);
        AdoptionPostImage adoptionPostImage = adoptionPostImageRepository.findByAdoptionPost(adoptionPost)
                .orElseThrow(AdoptionPostImageNotFoundException::new);

        adoptionPostImage.addMainImageUrl(imageUrl);
    }
}
