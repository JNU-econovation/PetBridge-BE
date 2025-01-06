/*package PetBridge.search.controller;

import PetBridge.adoptionPost.dto.AdoptionPostDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResponseEntity<List<AdoptionPostDTO>> searchAdoptionPosts(
            @RequestParam(required = false) String searchContent,
            @RequestParam(required = false) List<Long> tagIdList,
            @RequestParam(required = false) List<Long> breedIdList
    ){
        List<PostDTO> posts = searchService.searchPosts(searchContent, tagIdList, breedIdList);
        return ResponseEntity.ok(posts);
    }
}
*/