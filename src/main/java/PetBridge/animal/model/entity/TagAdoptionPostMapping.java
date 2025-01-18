package PetBridge.animal.model.entity;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagAdoptionPostMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Tag tag;

    @ManyToOne
    @JoinColumn
    private AdoptionPost adoptionPost;
}