package PetBridge.adoptionPost.controller;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.service.AdoptionPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/adoption-post")
public class AdoptionPostController {
    private final AdoptionPostService service;

    public AdoptionPostController(AdoptionPostService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createAdoptionPost(
            @RequestBody AdoptionPost post) {
        service.createAdoptionPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<AdoptionPost> updateAdoptionPost(
            @PathVariable Long id,
            @RequestBody AdoptionPost updatedPost) {
        AdoptionPost post = service.updateAdoptionPost(id, updatedPost);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdoptionPost(
            @PathVariable Long id) {
        service.deleteAdoptionPost(id);
        return ResponseEntity.noContent().build(); // 204 No Content 반환
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<AdoptionPost>> getAllAdoptionPosts() {
        List<AdoptionPost> posts = service.getAllAdoptionPosts();
        return ResponseEntity.ok(posts); // 200 OK와 함께 리스트 반환
    }

    // ID로 특정 분양글 조회
    @GetMapping("/{id}")
    public ResponseEntity<AdoptionPost> getAdoptionPostById(
            @PathVariable Long id) {
        AdoptionPost post = service.getAdoptionPostById(id);
        return ResponseEntity.ok(post); // 200 OK와 함께 해당 객체 반환
    }


}
