package PetBridge.adoptionPost.controller;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.service.AdoptionPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/adoption-post")
public class AdoptionPostController {
    private final AdoptionPostService service;

    public AdoptionPostController(AdoptionPostService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createAdoptionPost(@RequestBody AdoptionPost post) {
        service.createAdoptionPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<AdoptionPost> updateAdoptionPost(
            @PathVariable Long id,
            @RequestBody AdoptionPost updatedPost) {
        AdoptionPost post = service.updateAdoptionPost(id, updatedPost);
        return ResponseEntity.ok(post);
    }
     */

}
