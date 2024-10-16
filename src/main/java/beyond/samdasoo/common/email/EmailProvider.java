package beyond.samdasoo.common.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailProvider {

    private final JavaMailSender javaMailSender;

    private final String SIGNUP_TITLE = "회원가입 인증 메일";
    private final String CHANGE_PASSWORD_TITLE = "비밀번호 재설정 메일";

    public boolean sendCertificationMail(String email, String certificationNumber) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getCertificationMessage(certificationNumber);

            messageHelper.setTo(email);
            messageHelper.setSubject(SIGNUP_TITLE);
            messageHelper.setText(htmlContent,true);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean sendFindPasswordMail(){
        // todo : 진행 예정
        return true;
    }

    private String getCertificationMessage(String certificationNumber){
        String message = "";
        message +="<p>안녕하세요. SalesBoost 입니다. </p>";
        message +="<p>본인 확인을 위하여 아래의 인증 번호를 확인후, 회원가입 창에 입력해 주세요. </p>";
        message +="<p>해당 인증 코드의 유효시간은 <strong>5</strong> 분입니다.</p>";
        message+="<p>인증코드 : <strong style='font-size:17px;'>"+certificationNumber+"</strong></p>";
        return message;
    }
}
