package PetBridge.alert.service;

import PetBridge.alert.dto.AlertDTO;
import PetBridge.alert.exception.AlertNotFoundException;
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
}
