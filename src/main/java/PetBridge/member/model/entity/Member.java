package PetBridge.member.model.entity;

import PetBridge.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private Long searchHistoryCount;

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isSearchHistoryCountMax() {
        return searchHistoryCount >= 10;
    }

    public void increaseHistoryCountBySearching() {
        searchHistoryCount++;
    }

    public void decreaseHistoryCountByRemove() {
        searchHistoryCount--;
    }

    public void resetHistoryCountByRemoveAll() {
        searchHistoryCount = 0L;
    }
}
