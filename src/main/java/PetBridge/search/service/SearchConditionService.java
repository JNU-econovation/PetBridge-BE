package PetBridge.search.service;

import PetBridge.member.model.entity.Member;
import PetBridge.search.exception.SearchConditionNotFoundException;
import PetBridge.search.model.entity.SearchCondition;
import PetBridge.search.repository.SearchConditionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchConditionService {
    private final SearchConditionRepository searchConditionRepository;
    
    @Transactional(readOnly = true)
    public SearchCondition findByMemberOrThrow(Member member) {
        return searchConditionRepository.findByMember(member)
                .orElseThrow(SearchConditionNotFoundException::new);
    }
    
    @Transactional
    public void updateKeyword(SearchCondition searchCondition, String keyword) {
        if (keyword != null)
            searchCondition.updateKeyword(keyword);
    }

    @Transactional
    public void updateTagIdList(SearchCondition searchCondition, List<Long> tagIdList) {
        if (tagIdList == null && searchCondition.getTagIdList()!= null)
            return;
        if (tagIdList != null && !tagIdList.isEmpty())
            searchCondition.updateTagIdList(tagIdList);
    }

    @Transactional
    public void updateBreedIdList(SearchCondition searchCondition, List<Long> breedIdList) {
        if (breedIdList == null && searchCondition.getBreedIdList() != null)
            return;
        if (breedIdList != null && !breedIdList.isEmpty())
            searchCondition.updateBreedIdList(breedIdList);
    }

    @Transactional
    public void updateSortBy(SearchCondition searchCondition, String sortBy) {
        if (sortBy != null && !sortBy.isBlank())
            searchCondition.updateKeyword(sortBy);
    }
    
    @Transactional
    public void resetSearchCondition(Member member) {
        SearchCondition searchCondition = findByMemberOrThrow(member);
        searchCondition.resetSearchCondition();
    }

    @Transactional
    public void updateSearchCondition(SearchCondition searchCondition, String sortBy, String keyword, List<Long> tagIdList, List<Long> breedIdList) {
        updateKeyword(searchCondition,keyword);
        updateTagIdList(searchCondition,tagIdList);
        updateBreedIdList(searchCondition,breedIdList);
        updateSortBy(searchCondition,sortBy);
    }

    @Transactional
    public void addSearchCondition(Member member) {
        searchConditionRepository.save(
                SearchCondition.builder()
                        .member(member)
                        .keyword(null)
                        .sortBy(null)
                        .tagIdList(null)
                        .breedIdList(null)
                        .build()
        );
    }
}
