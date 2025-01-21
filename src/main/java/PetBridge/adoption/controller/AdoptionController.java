package PetBridge.adoption.controller;

import PetBridge.adoption.service.AdoptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/adoption")
@AllArgsConstructor
public class AdoptionController {
    private final AdoptionService adoptionService;
}
