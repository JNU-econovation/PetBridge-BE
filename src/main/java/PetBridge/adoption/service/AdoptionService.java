package PetBridge.adoption.service;

import PetBridge.adoption.dto.req.AdoptionFinalizationReq;
import PetBridge.adoption.dto.req.AdoptionReq;
import PetBridge.adoption.dto.res.GetAdoptionRes;
import PetBridge.adoption.exception.AdoptionNotFoundException;
import PetBridge.adoption.exception.AlreadyFinalizationAdoptionException;
import PetBridge.adoption.exception.AlreadyInProgressAdoptionException;
import PetBridge.adoption.model.entity.Adoption;
import PetBridge.adoption.repository.AdoptionRepository;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.service.AdoptionPostService;
import PetBridge.alert.service.AlertService;
import PetBridge.member.model.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AdoptionService {
    private final AdoptionRepository adoptionRepository;
    private final AdoptionPostService adoptionPostService;
    private final AlertService alertService;

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

        Adoption adoption = adoptionRepository.save(adoptionReq.toEntity(adoptionPost));

        sendAdoptionRequestAlert(adoption, adoptionPost);
    }

    @Transactional
    private void sendAdoptionRequestAlert(Adoption adoption, AdoptionPost adoptionPost) {
        alertService.addAdoptionRequestAlert(adoptionPost,adoption);
    }

    @Transactional
    public void finalizeAdoption(Long adoptionId, Member member, AdoptionFinalizationReq adoptionFinalizationReq) {
        Adoption adoption = findByIdOrThrow(adoptionId);
        AdoptionPost adoptionPost = adoption.getAdoptionPost();

        adoptionPost.finalizeAdoption();
        adoption.updatePetOwnerPhoneNumber(adoptionFinalizationReq.petOwnerPhoneNumber());

        sendAdoptionFinalizationAlert(adoption, adoptionPost);
    }

    @Transactional
    private void sendAdoptionFinalizationAlert(Adoption adoption, AdoptionPost adoptionPost) {
        Member petOwner = adoptionPost.getMember();
        Member adoptionSeeker = adoptionPost.getAdoptionSeeker();

        alertService.addAdoptionFinalizationAlert(adoptionPost,adoption,petOwner);
        alertService.addAdoptionFinalizationAlert(adoptionPost, adoption, adoptionSeeker);
    }

    @Transactional
    public void cancelAdoption(Long adoptionId, Member member) {
        Adoption adoption = findByIdOrThrow(adoptionId);
        AdoptionPost adoptionPost = adoption.getAdoptionPost();

        adoptionPost.cancelRequestAdoption();
        adoptionPost.cancelAdoptionFinalization();

        sendAdoptionCancelAlert(adoptionPost, adoption);

        adoption.softDelete();
    }

    @Transactional
    private void sendAdoptionCancelAlert(AdoptionPost adoptionPost, Adoption adoption) {
        Member petOwner = adoptionPost.getMember();
        Member adoptionSeeker = adoptionPost.getAdoptionSeeker();

        alertService.addAdoptionCancelAlert(adoptionPost, adoption, petOwner);
        alertService.addAdoptionCancelAlert(adoptionPost, adoption, adoptionSeeker);
    }

    @Transactional(readOnly = true)
    public GetAdoptionRes getAdoption(Long adoptionId) {
        Adoption adoption = findByIdOrThrow(adoptionId);

        return GetAdoptionRes.from(adoption.getAdoptionPost(),adoption);
    }
}
