package PetBridge.alert.model.entity;

import PetBridge.adoption.model.entity.Adoption;
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
public class Alert extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String alertType;

    @ManyToOne
    @JoinColumn
    private Adoption adoption;

    @ManyToOne
    @JoinColumn
    private Member member;

    private Boolean isUnRead;

    public void changeToRead() {
        isUnRead = false;
    }
}
