package PetBridge.adoption.service;

import PetBridge.adoption.dto.req.AdoptionFinalizationReq;
import PetBridge.adoption.dto.req.AdoptionReq;
import PetBridge.adoption.exception.AdoptionNotFoundException;
import PetBridge.adoption.exception.AlreadyFinalizationAdoptionException;
import PetBridge.adoption.exception.AlreadyInProgressAdoptionException;
import PetBridge.adoption.model.entity.Adoption;
import PetBridge.adoption.repository.AdoptionRepository;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.service.AdoptionPostService;
import PetBridge.member.model.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AdoptionService {
    private final AdoptionRepository adoptionRepository;
    private final AdoptionPostService adoptionPostService;

    @Transactional(readOnly = true)
    public Adoption findByIdOrThrow (Long adoptionId) {
        return adoptionRepository.findById(adoptionId)
                .orElseThrow(AdoptionNotFoundException::new);
    }

    @Transactional
    public void adoptionRequest(Long adoptionPostId, Member member, AdoptionReq adoptionReq) {
        AdoptionPost adoptionPost = adoptionPostService.findByIdOrThrow(adoptionPostId);

        if(adoptionPost.isAdoptionInProgress())
            throw new AlreadyInProgressAdoptionException();

        if(adoptionPost.isAdoptionFinalization())
            throw new AlreadyFinalizationAdoptionException();

        adoptionPost.requestAdoption(member);

        adoptionRepository.save(adoptionReq.toEntity(adoptionPost));
    }

    @Transactional
    public void finalizeAdoption(Long adoptionId, Member member, AdoptionFinalizationReq adoptionFinalizationReq) {
        Adoption adoption = findByIdOrThrow(adoptionId);
        AdoptionPost adoptionPost = adoption.getAdoptionPost();

        adoptionPost.finalizeAdoption();
        adoption.updatePetOwnerPhoneNumber(adoptionFinalizationReq.petOwnerPhoneNumber());
    }
}
