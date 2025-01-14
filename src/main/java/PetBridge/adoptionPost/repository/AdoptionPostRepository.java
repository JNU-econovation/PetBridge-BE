package PetBridge.adoptionPost.repository;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.member.model.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionPostRepository extends JpaRepository<AdoptionPost, Long> {

    // 최신순 (ID를 기준으로 내림차순 정렬)
    Slice<AdoptionPost> findAllByOrderByIdDesc(Pageable pageable);
    // 조회수 순 (조회수를 기준으로 내림차순 정렬)
    Slice<AdoptionPost> findAllByOrderByClickCountDesc(Pageable pageable);
    // 찜수 순 (찜수를 기준으로 내림차순 정렬)
    Slice<AdoptionPost> findAllByOrderByWishCountDesc(Pageable pageable);

}
