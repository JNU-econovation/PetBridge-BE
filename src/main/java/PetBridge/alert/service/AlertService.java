package PetBridge.alert.service;

import PetBridge.adoption.model.entity.Adoption;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.alert.dto.AlertDTO;
import PetBridge.alert.exception.AlertNotFoundException;
import PetBridge.alert.mapper.AlertMapper;
import PetBridge.alert.model.entity.Alert;
import PetBridge.alert.repository.AlertRepository;
import PetBridge.member.model.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AlertService {
    private final AlertRepository alertRepository;
    private final AlertMapper alertMapper;

    @Transactional(readOnly = true)
    public Alert findByIdOrThrow(Long alertId) {
        return alertRepository.findById(alertId)
                .orElseThrow(AlertNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Long getUnreadAlertCount(Member member) {
        return findListByMember(member)
                .stream()
                .filter(Alert::getIsUnRead)
                .count();
    }

    @Transactional(readOnly = true)
    public List<Alert> findListByMember(Member member) {
        return alertRepository.findByMember(member);
    }

    @Transactional(readOnly = true)
    public List<AlertDTO> getAlertDTOList(Member member) {
        return findListByMember(member)
                .stream()
                .map(AlertDTO::from)
                .toList();
    }

    @Transactional
    public void changeToRead(Long alertId) {
        Alert alert = findByIdOrThrow(alertId);
        alert.changeToRead();
    }

    @Transactional
    public void deleteByIdOrThrow(Long alertId) {
        Alert alert = findByIdOrThrow(alertId);
        alertRepository.delete(alert);
    }

    //반려인에게 전송필요
    @Transactional
    public void addAdoptionRequestAlert (AdoptionPost adoptionPost, Adoption adoption){
        Alert alert = alertMapper.toAdoptionRequestAlert(adoptionPost, adoption);
        alertRepository.save(alert);
    }

    //양쪽에 전송필요
    @Transactional
    public void addAdoptionCancelAlert (AdoptionPost adoptionPost, Adoption adoption, Member member) {
        Alert alert = alertMapper.toAdoptionCancelAlert(adoptionPost, adoption, member);
        alertRepository.save(alert);
    }

    //양쪽에 전송 필요
    @Transactional
    public void addAdoptionFinalizationAlert(AdoptionPost adoptionPost, Adoption adoption, Member member) {
        Alert alert = alertMapper.toAdoptionFinalizationAlert(member, adoptionPost,adoption);
        alertRepository.save(alert);
    }

    @Transactional
    public void addNormalAlertOfWelcome(Member member) {
        Alert alert = alertMapper.toNormalAlertOfWelcome(member);
        alertRepository.save(alert);
    }
}
