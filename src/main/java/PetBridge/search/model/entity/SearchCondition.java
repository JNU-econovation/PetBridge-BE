package PetBridge.search.model.entity;

import PetBridge.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchCondition {
    private static final String SORT_DEFAULT = "recent";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    private String keyword;

    private String sortBy;

    private List<Long> tagIdList;

    private List<Long> breedIdList;

    public void updateKeyword (String keyword) {
        this.keyword = keyword;
    }

    public void updateSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public void updateTagIdList(List<Long> tagIdList) {
        this.tagIdList = tagIdList;
    }

    public void updateBreedIdList(List<Long> breedIdList) {
        this.breedIdList = breedIdList;
    }

    public void resetSearchCondition() {
        this.keyword = null;
        this.sortBy = SORT_DEFAULT;
        this.tagIdList = null;
        this.breedIdList = null;
    }
}
