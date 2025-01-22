package PetBridge.search.repository;

import PetBridge.member.model.entity.Member;
import PetBridge.search.model.entity.SearchCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SearchConditionRepository extends JpaRepository<SearchCondition, Long> {
    Optional<SearchCondition> findByMember(Member member);
}
