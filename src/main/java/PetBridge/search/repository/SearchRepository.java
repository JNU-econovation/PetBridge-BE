package PetBridge.search.repository;

import PetBridge.member.model.entity.Member;
import PetBridge.search.model.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SearchRepository extends JpaRepository<SearchHistory, Long> {
    List<SearchHistory> findByMemberOrderByCreatedDateDesc(Member member);

    Optional<SearchHistory> findFirstByMemberOrderByCreatedDateAsc(Member member);

    Optional<SearchHistory> findByMemberAndId(Member member, Long searchHistoryId);

    void deleteAllByMember(Member member);
}
