package PetBridge.adoptionPost.service;

import PetBridge.adoptionPost.dto.AdoptionPostSortDTO;
import PetBridge.adoptionPost.mapper.AdoptionPostMapper;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.repository.AdoptionPostRepository;
import PetBridge.animal.model.entity.Breed;
import PetBridge.animal.model.entity.Tag;
import PetBridge.animal.service.BreedService;
import PetBridge.animal.service.TagService;
import PetBridge.member.model.entity.Member;
import PetBridge.search.service.SearchService;
import PetBridge.wish.model.entity.Wish;
import PetBridge.wish.service.WishService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AdoptionPostSearchService {
    private final AdoptionPostRepository adoptionPostRepository;
    private final SearchService searchService;
    private final TagService tagService;
    private final BreedService breedService;
    private final WishService wishService;
    private final AdoptionPostMapper adoptionPostMapper;

    @Transactional
    public List<AdoptionPostSortDTO> getAdoptionPosts(String sortBy, Member member, String keyword, List<Long> tagIdList, List<Long> breedIdList) {
        saveSearchHistory(member, keyword);

        List<Tag> tagList = getTagList(tagIdList);
        List<Breed> breedList = getBreedList(breedIdList);


        List<AdoptionPost> searchedAdoptionPostList = adoptionPostRepository.findAllFilteredAdoptionPost(sortBy, keyword, tagList, breedList);
        List<AdoptionPost> wishPostList = filterWishPost(searchedAdoptionPostList, member);

        return searchedAdoptionPostList
                .stream()
                .map(adoptionPost -> {
                    boolean isWishPost = wishPostList.contains(adoptionPost);
                    return adoptionPostMapper.toAdoptionPostSortDTO(adoptionPost,isWishPost);
                })
                .toList();


    }

    @Transactional(readOnly = true)
    private List<Breed> getBreedList(List<Long> breedIdList) {
        if (breedIdList == null)
            return null;
        return breedService.findByIdListOrThrow(breedIdList);
    }

    @Transactional(readOnly = true)
    private List<Tag> getTagList(List<Long> tagIdList) {
        if (tagIdList == null )
            return null;
        return tagService.findByIdListOrThrow(tagIdList);
    }

    //게시글 리스트 중에서 유저의 좋아요 게시글을 필터링 하는 메소드
    @Transactional(readOnly = true)
    private List<AdoptionPost> filterWishPost(List<AdoptionPost> adoptionPostList, Member member) {
         List<AdoptionPost> wishPostList =  wishService.findListByMember(member)
                 .stream()
                 .map(Wish::getAdoptionPost)
                 .toList();

         return adoptionPostList
                 .stream()
                 .filter(wishPostList::contains)
                 .toList();
    }

    @Transactional
    private void saveSearchHistory(Member member, String keyword) {
        if (keyword == null)
            return;
        searchService.addSearchHistory(member, keyword);
    }

}
