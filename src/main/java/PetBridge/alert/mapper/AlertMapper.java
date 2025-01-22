package PetBridge.alert.mapper;

import PetBridge.adoption.model.entity.Adoption;
import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.member.model.entity.Member;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import PetBridge.alert.model.entity.Alert;

@Component
@NoArgsConstructor
public class AlertMapper {
    //AlertType
    private static final String ADOPTION_REQUEST_ALERT = "adoptionRequestAlert";
    private static final String ADOPTION_CANCEL_ALERT = "adoptionCancelAlert";
    private static final String ADOPTION_FINALIZATION_ALERT = "adoptionRequestAlert";
    private static final String NORMAL_ALERT = "normal";

    //AlertTitle
    private static final String ADOPTION_REQUEST_ALERT_TITLE = "입양 요청 알림";
    private static final String ADOPTION_FINALIZATION_ALERT_TITLE = "입양 체결 알림";
    private static final String ADOPTION_CANCEL_ALERT_TITLE = "입양 취소 알림";
    private static final String WELCOME_TITLE = "환영해요!";

    //AlertContent
    private static final String ADOPTION_REQUEST_ALERT_CONTENT = "유저가 입양 요청을 하였습니다! 입양을 체결해주세요!";
    private static final String ADOPTION_CANCEL_ALERT_CONTENT = "글의 입양 요청이 취소되었습니다.";
    private static final String ADOPTION_FINALIZATION_ALERT_CONTENT = "글의 입양이 최종적으로 체결되었습니다! 상대방의 연락처를 확인하고 연락해주세요!";
    private static final String WELCOME_CONTENT = "펫브릿지에 오신걸 환영합니다! 무료 동물입양 매칭 NO.1 플랫폼 펫브릿지";

    public Alert toAdoptionRequestAlert(AdoptionPost adoptionPost, Adoption adoption){
        String adoptionSeekerNickname = adoptionPost.getAdoptionSeeker().getNickname();
        return Alert.builder()
                .title(ADOPTION_REQUEST_ALERT_TITLE)
                .content(adoptionSeekerNickname+ADOPTION_REQUEST_ALERT_CONTENT)
                .alertType(ADOPTION_REQUEST_ALERT)
                .adoption(adoption)
                .member(adoptionPost.getMember())
                .build();
    }


    public Alert toAdoptionCancelAlert(AdoptionPost adoptionPost, Adoption adoption, Member member) {
        String adoptionPostTitle = adoptionPost.getTitle();
        return Alert.builder()
                .title(ADOPTION_CANCEL_ALERT_TITLE)
                .content(adoptionPostTitle + ADOPTION_CANCEL_ALERT_CONTENT)
                .alertType(ADOPTION_CANCEL_ALERT)
                .adoption(adoption)
                .member(member)
                .build();
    }

    public Alert toAdoptionFinalizationAlert(Member member, AdoptionPost adoptionPost, Adoption adoption) {
        String adoptionPostTitle = adoptionPost.getTitle();
        return Alert.builder()
                .title(ADOPTION_FINALIZATION_ALERT_TITLE)
                .content(adoptionPostTitle+ ADOPTION_FINALIZATION_ALERT_CONTENT)
                .alertType(ADOPTION_FINALIZATION_ALERT)
                .adoption(adoption)
                .member(member)
                .build();
    }

    public Alert toNormalAlertOfWelcome(Member member) {
        return Alert.builder()
                .title(WELCOME_TITLE)
                .content(WELCOME_CONTENT)
                .alertType(NORMAL_ALERT)
                .adoption(null)
                .member(member)
                .build();
    }
}
