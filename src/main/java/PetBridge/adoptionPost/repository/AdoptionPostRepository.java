package PetBridge.adoptionPost.repository;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdoptionPostRepository extends JpaRepository<AdoptionPost, Long>, AdoptionPostCustom{

    Optional<AdoptionPost> findByIdAndMember(Long postId, Member member);

    @Modifying
    @Query("UPDATE AdoptionPost a SET a.clickCount = a.clickCount + 1 WHERE a = :adoptionPost")
    void incrementClickCount(@Param("adoptionPost") AdoptionPost adoptionPost);

    @Modifying
    @Query("UPDATE AdoptionPost a SET a.wishCount = a.wishCount -1 WHERE a = :adoptionPost")
    void decrementWishCount(@Param("adoptionPost")AdoptionPost adoptionPost);

    @Modifying
    @Query("UPDATE AdoptionPost a SET a.wishCount = a.wishCount + 1 WHERE a = :adoptionPost")
    void incrementWishCount(@Param("adoptionPost")AdoptionPost adoptionPost);

    List<AdoptionPost> findByMember(Member member);
}
