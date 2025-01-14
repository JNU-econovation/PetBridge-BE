package PetBridge.wish.repository;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.member.model.entity.Member;
import PetBridge.wish.model.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {
    Optional<Wish> findByMemberAndAdoptionPost(Member member, AdoptionPost adoptionPost);

    boolean existsByMemberAndAdoptionPost(Member member, AdoptionPost adoptionPost);
}
