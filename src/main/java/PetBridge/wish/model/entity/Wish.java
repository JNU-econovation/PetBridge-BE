package PetBridge.wish.model.entity;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private AdoptionPost adoptionPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;
}
