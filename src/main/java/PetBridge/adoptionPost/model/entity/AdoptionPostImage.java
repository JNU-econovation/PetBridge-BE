package PetBridge.adoptionPost.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdoptionPostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mainImageUrl;

    private List<String> imageUrlList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private AdoptionPost adoptionPost;

    public void addImageUrl(String imageUrl) {
        this.imageUrlList.add(imageUrl);
    }

    public void addMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }
}
