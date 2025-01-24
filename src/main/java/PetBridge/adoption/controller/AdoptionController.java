package PetBridge.adoption.controller;

import PetBridge.adoption.dto.req.AdoptionFinalizationReq;
import PetBridge.adoption.dto.req.AdoptionReq;
import PetBridge.adoption.dto.res.GetAdoptionRes;
import PetBridge.adoption.service.AdoptionService;
import PetBridge.auth.jwt.annotation.ValidMember;
import PetBridge.member.model.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/adoption")
@AllArgsConstructor
public class AdoptionController {
    private final AdoptionService adoptionService;

    @PostMapping("/{adoptionPostId}")
    public ResponseEntity<Void> adoptionRequest(
            @PathVariable("adoptionPostId") Long adoptionPostId,
            @ValidMember Member member,
            @RequestBody AdoptionReq adoptionReq
    ) {
        adoptionService.adoptionRequest(adoptionPostId, member, adoptionReq);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/finalization/{adoptionId}")
    public ResponseEntity<Void> finalizeAdoption(
            @PathVariable("adoptionId") Long adoptionId,
            @ValidMember Member member,
            @RequestBody AdoptionFinalizationReq adoptionFinalizationReq
            ) {
        adoptionService.finalizeAdoption(adoptionId, member, adoptionFinalizationReq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/cancel/{adoptionId}")
    public ResponseEntity<Void> cancelAdoption(
        @PathVariable("adoptionId") Long adoptionId,
        @ValidMember Member member
    ) {
        adoptionService.cancelAdoption(adoptionId, member);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping("/{adoptionId}")
    public ResponseEntity<GetAdoptionRes> getAdoption(
            @PathVariable("adoptionId") Long adoptionId
    ){
        GetAdoptionRes adoptionRes = adoptionService.getAdoption(adoptionId);
        return ResponseEntity.status(HttpStatus.OK).body(adoptionRes);
    }

}