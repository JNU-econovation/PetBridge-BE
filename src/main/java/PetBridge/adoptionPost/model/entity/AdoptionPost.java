package PetBridge.adoptionPost.model.entity;

import PetBridge.adoptionPost.dto.AdoptionPostUpdateDTO;
import PetBridge.animal.model.entity.Breed;
import PetBridge.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdoptionPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adoption_seeker_id")
    private Member adoptionSeeker;

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
    private Double weight;

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
    private Long clickCount;

    //찜 수
    private Long wishCount;

    private String mainImage;

    private List<String> imageList;

    public void updateAdoptionPost(AdoptionPostUpdateDTO dto) {
        this.title = dto.title();
        this.subTitle = dto.subTitle();
        this.weight = dto.weight();
        this.age =dto.age();
        this.isNeutered = dto.isNeutered();
        this.isAdoptionContractRequired = dto.isAdoptionContractRequired();
        this.meetingPlace = dto.meetingPlace();
        this.likes = dto.likes();
        this.hates = dto.hates();
        this.currentDiseases = dto.currentDiseases();
        this. pastDiseases = dto.pastDiseases();
        this.petOwnerRequirement = dto.petOwnerRequirement();
        this.detailContent = dto.detailContent();
        this.adoptionFinalizationStatus = dto.adoptionFinalizationStatus();
    }

    public boolean isAdoptionInProgress() {
        return this.adoptionSeeker != null;
    }

    public boolean isAdoptionFinalization() {
        return this.adoptionFinalizationStatus;
    }

    public void requestAdoption(Member adoptionSeeker) {
        this.adoptionSeeker = adoptionSeeker;
    }

    public void cancelRequestAdoption() {
        this.adoptionSeeker = null;
    }

    public void cancelAdoptionFinalization() {
        this.adoptionFinalizationStatus = false;
    }

    public void finalizeAdoption() {
        this.adoptionFinalizationStatus = true;
    }
}
