package PetBridge.email.controller;

import PetBridge.email.dto.Request.SendEmailCodeReq;
import PetBridge.email.dto.Request.ValidationEmailCodeReq;
import PetBridge.email.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/emailCode")
    public ResponseEntity<Void> sendEmailCode (
            @RequestBody SendEmailCodeReq sendEmailCodeReq
    ) {
        emailService.sendEmailCode(sendEmailCodeReq.email());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/emailCode")
    public ResponseEntity<Void> validationEmailCode(
            @RequestBody ValidationEmailCodeReq validationEmailCodeReq
    ) {
        emailService.validationEmailCode(validationEmailCodeReq.email(), validationEmailCodeReq.emailCode());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
