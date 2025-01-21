package PetBridge.alert.dto;

import PetBridge.alert.model.entity.Alert;

import java.time.format.DateTimeFormatter;

public record AlertDTO (
        Long id,
        String title,
        String content,
        String alertType,
        String createdDate,
        Long adoptionId,
        Boolean isUnRead
) {
    public static AlertDTO from(Alert alert) {
        Long adoptionId = null;

        if (alert.getAdoption() != null)
            adoptionId = alert.getAdoption().getId();

        return new AlertDTO(
                alert.getId(),
                alert.getTitle(),
                alert.getContent(),
                alert.getAlertType(),
                alert.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                adoptionId,
                alert.getIsUnRead()
        );
    }
}
