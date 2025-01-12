package PetBridge.search.service;

import PetBridge.member.model.entity.Member;
import PetBridge.search.dto.SearchHistoryDTO;
import PetBridge.search.exception.InvalidAllDeleteRequestException;
import PetBridge.search.exception.NotFoundSearchHistoryException;
import PetBridge.search.model.entity.SearchHistory;
import PetBridge.search.repository.SearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchService {
    private SearchRepository searchRepository;


    @Transactional
    public SearchHistory findByMemberAndIdOrThrow(Member member, Long searchHistoryId){
        return searchRepository.findByMemberAndId(member, searchHistoryId)
                .orElseThrow(NotFoundSearchHistoryException::new );
    }

    @Transactional
    public void deleteHistoryById(Long searchHistoryId) {
        searchRepository.deleteById(searchHistoryId);
    }

    @Transactional
    private void deleteSingleSearchHistory(Member member, Long searchHistoryId) {
        SearchHistory historyToDelete = findByMemberAndIdOrThrow(member, searchHistoryId);
        deleteHistoryById(historyToDelete.getId());
    }

    @Transactional
    private void deleteAllSearchHistory(Member member) {
        searchRepository.deleteAllByMember(member);
    }

    //오래된 검색 기록 조회
    @Transactional(readOnly = true)
    public SearchHistory findOldestHistoryOfMemberOrThrow(Member member){
        return searchRepository.findFirstByMemberOrderByCreatedDateAsc(member)
                .orElseThrow(NotFoundSearchHistoryException::new);
    }

    //검색 기록 저장
    @Transactional
    public void addSearchHistory(Member member, String keyword) {
        if(member.isSearchHistoryCountMax()) {
            SearchHistory oldestHistory = findOldestHistoryOfMemberOrThrow(member);
            deleteHistoryById(oldestHistory.getId());
        }

        searchRepository.save(
                SearchHistory.builder()
                        .keyword(keyword)
                        .member(member)
                        .build()
        );
        member.increaseHistoryCountBySearching();
    }

    //최근 검색 기록 10개 조회
    @Transactional(readOnly = true)
    public List<SearchHistoryDTO> getRecentSearchHistory(Member member) {
        return searchRepository.findByMemberOrderByCreatedDateDesc(member)
                .stream()
                .map(SearchHistoryDTO::from)
                .toList();
    }

    @Transactional
    public void deleteSingleHistory(Member member, Long searchHistoryId) {
        deleteSingleSearchHistory(member, searchHistoryId);
        member.decreaseHistoryCountByRemove();
    }

    @Transactional
    public void deleteAllHistory(Member member, Boolean isRemoveAll) {
        if(!isRemoveAll)
            throw new InvalidAllDeleteRequestException();

        deleteAllSearchHistory(member);
        member.resetHistoryCountByRemoveAll();
    }
}
