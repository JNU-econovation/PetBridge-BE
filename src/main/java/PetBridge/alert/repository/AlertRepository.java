package PetBridge.alert.repository;

import PetBridge.alert.model.entity.Alert;
import PetBridge.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByMember(Member member);
}
