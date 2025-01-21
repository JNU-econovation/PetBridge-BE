package PetBridge.animal.controller;

import PetBridge.animal.dto.AnimalTagListDTO;
import PetBridge.animal.dto.BreedTagListDTO;
import PetBridge.animal.dto.res.GetAnimalTagsRes;
import PetBridge.animal.dto.res.GetBreedTagsRes;
import PetBridge.animal.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/animal")
public class AnimalController {
    private AnimalService animalService;

    @GetMapping("/tags")
    public ResponseEntity<GetAnimalTagsRes> getAnimalTags () {
        AnimalTagListDTO animalTagListDTO = animalService.getAnimalTags();
        return ResponseEntity.status(HttpStatus.OK).body(GetAnimalTagsRes.from(animalTagListDTO));
    }

    @GetMapping("/breeds")
    public ResponseEntity<GetBreedTagsRes> getBreedTagsRes () {
        BreedTagListDTO breedTagListDTO = animalService.getBreedTags();
        return ResponseEntity.status(HttpStatus.OK).body(GetBreedTagsRes.from(breedTagListDTO));
    }
}
