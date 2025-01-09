package PetBridge.adoptionPost.repository;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionPostRepository extends JpaRepository<AdoptionPost, Long> {

    // 최신순 (ID를 기준으로 내림차순 정렬)
    List<AdoptionPost> findAllByOrderByIdDesc();
    // 조회수 순 (조회수를 기준으로 내림차순 정렬)
    List<AdoptionPost> findAllByOrderByClickCountDesc();
    // 찜수 순 (찜수를 기준으로 내림차순 정렬)
    List<AdoptionPost> findAllByOrderByWishCountDesc();

}
