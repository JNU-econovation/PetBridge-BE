package PetBridge.adoption.model.entity;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.common.entity.BaseEntity;
import PetBridge.member.model.entity.Member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Adoption extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private AdoptionPost adoptionPost;

    private String adoptionSeekerRequirement;

    private String petOwnerPhoneNumber;

    private String adoptionSeekerPhoneNumber;

    public void updatePetOwnerPhoneNumber(String petOwnerPhoneNumber) {
        this.petOwnerPhoneNumber = petOwnerPhoneNumber;
    }
}
