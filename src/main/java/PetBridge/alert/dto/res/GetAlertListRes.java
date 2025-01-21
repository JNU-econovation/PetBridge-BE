package PetBridge.alert.dto.res;

import PetBridge.alert.dto.AlertDTO;

import java.util.List;

public record GetAlertListRes(
        List<AlertDTO> alertList
) {
}
