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
    //분양 신청자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adoption_seeker_id")
    private Member adoptionSeeker;

    //품종 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id", nullable = false)
    private Breed breed;

    //분양 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    //제목
    private String title;

    //부제목
    private String subTitle;

    //체중
    private Long weight;

    //나이
    private Long age;

    //중성화여부
    private Boolean isNeutered;

    //분양 계약서 작성 여부
    private Boolean isAdoptionContractRequired;

    //만남 장소
    private String meetingPlace;

    //좋아하는것
    @Column(columnDefinition = "Text")
    private String likes;//like는 MySQL의 예약어이므로 예약어 충돌 방지를 위해 likes로 변경함

    //싫어하는것
    private String hates;

    //현재 질병
    private String currentDiseases;

    //과거 질병
    private String pastDiseases;

    //반려 동물 주인 요구 사항
    @Column(columnDefinition = "Text")
    private String petOwnerRequirement;

    //상세 내용
    private String detailContent;

    //분양 완료 여부
    private Boolean adoptionFinalizationStatus = false;

    //클릭 수(조회수)
    private Long clickCount = 0L;

    //찜 수
    private Long wishCount = 0L;


}
