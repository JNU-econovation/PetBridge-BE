package PetBridge.adoptionPost.model.entity;

import PetBridge.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class AdoptionPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adoption_seeker_id")
    private Member adoptionSeeker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id", nullable = false)
    private Breed breed;

    private String title;
    private String subTitle;
    private Long weight;
    private Long age;
    private Boolean isNeutered;
    private Boolean isAdoptionContractRequired;
    private String meetingPlace;

    @Column(columnDefinition = "Text")
    private String like;
    private String hate;

    private String currentDiseases;
    private String pastDiseases;

    @Column(columnDefinition = "Text")
    private String petOwnerRequirement;

    private String detailContent;

    private Boolean adoptionFinalizationStatus = false;

    private Long clickCount = 0L;

    private Long wishCount = 0L;

    protected AdoptionPost() {
    }

}
