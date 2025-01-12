package PetBridge.email.service;

import PetBridge.email.exception.EmailNotFoundException;
import PetBridge.email.exception.FailToSendEmailCode;
import PetBridge.email.model.entity.Email;
import PetBridge.email.repository.EmailRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailService {
    private final String EMAIL_TITLE = "PetBridge 서비스 인증코드 안내";

    private final EmailRepository emailRepository;
    private final JavaMailSender emailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = javaMailSender;
    }

    @Transactional(readOnly = true)
    public Email findByEmailOrThrow (String email) {
        return emailRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);
    }

    @Transactional
    public void sendEmailCode(String email) {
        String emailCode = createEmailCode();
        SimpleMailMessage emailForm = createEmailForm(email, emailCode);

        try {
            emailSender.send(emailForm);
        } catch (RuntimeException e) {
            throw new FailToSendEmailCode();
        }

        emailRepository.save(createEmailEntity(email, emailCode));
    }

    private Email createEmailEntity(String email, String emailCode) {
        return new Email (email, emailCode);
    }

    private String createEmailCode () {
        Integer emailCode = (int) (Math.random() * (999999 - 100000)) + 100000; //6자리 랜덤한 수 생성
        return String.valueOf(emailCode);
    }

    private SimpleMailMessage createEmailForm(String email, String emailCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(EMAIL_TITLE);
        message.setText(emailCode);

        return message;
    }

    @Transactional
    public void validationEmailCode(String email, String emailCode) {
        Email foundEmail = findByEmailOrThrow(email);
        emailRepository.delete(foundEmail);
    }
}
