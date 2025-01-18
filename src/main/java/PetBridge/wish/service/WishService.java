package PetBridge.wish.service;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.service.AdoptionPostService;
import PetBridge.member.model.entity.Member;
import PetBridge.wish.exception.NotFoundWishException;
import PetBridge.wish.model.entity.Wish;
import PetBridge.wish.repository.WishRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class WishService {
    private WishRepository wishRepository;
    private AdoptionPostService adoptionPostService;

    @Transactional(readOnly = true)
    public Wish findByMemberAndPostOrThrow(Member member, AdoptionPost adoptionPost) {
        return wishRepository.findByMemberAndAdoptionPost(member, adoptionPost)
                .orElseThrow(NotFoundWishException::new);
    }

    @Transactional(readOnly = true)
    public boolean existsByMemberAndAdoptionPost(Member member, AdoptionPost adoptionPost) {
        return wishRepository.existsByMemberAndAdoptionPost(member, adoptionPost);
    }

    @Transactional
    public void addWish(Member member, Long postId) {
        AdoptionPost adoptionPost = adoptionPostService.findByIdOrThrow(postId);
        boolean existByMemberAndPost = existsByMemberAndAdoptionPost(member, adoptionPost);

        if (existByMemberAndPost)
            throw new DuplicateRequestException();

        adoptionPostService.increaseWishCountById(adoptionPost.getId());
        wishRepository.save(Wish.builder()
                .member(member)
                .adoptionPost(adoptionPost)
                .build()
        );
    }

    @Transactional
    public void deleteWish(Member member, Long postId) {
        AdoptionPost adoptionPost = adoptionPostService.findByIdOrThrow(postId);
        adoptionPostService.decreaseWishCountById(adoptionPost.getId());
        Wish wish = findByMemberAndPostOrThrow(member, adoptionPost);
        wishRepository.delete(wish);
    }
}
