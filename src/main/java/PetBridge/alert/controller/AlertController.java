package PetBridge.alert.controller;

import PetBridge.alert.dto.AlertDTO;
import PetBridge.alert.dto.res.GetAlertListRes;
import PetBridge.alert.dto.res.GetUnReadAlertCountRes;
import PetBridge.alert.model.entity.Alert;
import PetBridge.alert.service.AlertService;
import PetBridge.auth.jwt.annotation.ValidMember;
import PetBridge.member.model.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alert")
@AllArgsConstructor
public class AlertController {
    private final AlertService alertService;

    @GetMapping("/unread")
    public ResponseEntity<GetUnReadAlertCountRes> getUnreadAlertCount (
            @ValidMember Member member
    ) {
        Long unReadAlertCount = alertService.getUnreadAlertCount(member);
        return ResponseEntity.status(HttpStatus.OK).body(new GetUnReadAlertCountRes(unReadAlertCount));
    }

    @GetMapping("/list")
    public ResponseEntity<GetAlertListRes> getAlertList(
            @ValidMember Member member
    ) {
        List<AlertDTO> alertDTOList = alertService.getAlertDTOList(member);
        return ResponseEntity.status(HttpStatus.OK).body(new GetAlertListRes(alertDTOList));
    }

    @PutMapping("/{alertId}")
    public ResponseEntity<Void> changeToRead (
            @ValidMember Member member,
            @PathVariable("alertId") Long alertId
    ) {
        alertService.changeToRead(alertId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{alertId}")
    public ResponseEntity<Void> deleteAlert(
            @ValidMember Member member,
            @PathVariable("alertId") Long alertId
    ) {
        alertService.deleteByIdOrThrow(alertId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
