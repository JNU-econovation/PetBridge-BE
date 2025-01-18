package PetBridge.animal.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagBreedMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Tag animalTypeTag;

    @ManyToOne
    @JoinColumn
    private Tag animalSizeTag;

    @ManyToOne
    @JoinColumn
    private Breed breed;
}
