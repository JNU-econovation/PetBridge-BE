package PetBridge.adoptionPost.model.entity;

import PetBridge.breed.model.entity.Breed;
import PetBridge.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)//엔티티 생성 시 명시적으로 필드를 설정할 수 있도록 도와주는 디자인 패턴
@AllArgsConstructor//모든 필드를 매개변수로 받는 생성자를 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)//매개변수가 없는 기본 생성자 생성
public class AdoptionPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ManyToOne 매핑 순서 변경함 : ManyToOne관계에서 참조되는 엔티티가 먼저 정의되어야 외래키 제약 조건이 올바르게 설정됨
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adoption_seeker_id")
    private Member adoptionSeeker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id", nullable = false)
    private Breed breed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private String title;

    private String subTitle;

    private Long weight;

    private Long age;

    private Boolean isNeutered;

    private Boolean isAdoptionContractRequired;

    private String meetingPlace;

    @Column(columnDefinition = "Text")
    private String likes;//like는 MySQL의 예약어이므로 예약어 충돌 방지를 위해 likes로 변경함

    private String hates;

    private String currentDiseases;

    private String pastDiseases;

    @Column(columnDefinition = "Text")
    private String petOwnerRequirement;

    private String detailContent;

    private Boolean adoptionFinalizationStatus = false;

    private Long clickCount = 0L;

    private Long wishCount = 0L;


}
