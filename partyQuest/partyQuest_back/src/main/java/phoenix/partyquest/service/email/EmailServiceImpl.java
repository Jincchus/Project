package phoenix.partyquest.service.email;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public static final String key = createKey();

    // 메시지 생성
    private MimeMessage createMessage(String receiver)throws Exception{
        log.info("send to :"+ receiver);
        log.info("Authentication code : "+key);
        MimeMessage  message = mailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, receiver);//보내는 대상
        message.setSubject("[파티퀘스트]이메일 인증 코드입니다.");//제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> PARTYT QUEST 회원가입</h1>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= key+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("gussla2206@gmail.com","PQ_MANAGER"));//보내는 사람

        return message;
    }

    // 인증 코드 생성 메서드
    public static String createKey(){
        StringBuffer key = new StringBuffer();
        
        // 인증 코드 랜덤으로 생성
        Random rnd = new Random();

        for(int i = 0; i<8; i++){ // 8자리 인증코드
            int index = rnd.nextInt(3); // 0~2까지 랜덤

            switch (index){
                case 0:
                    key.append((char) ((int)(rnd.nextInt(26))+ 97)); // a~z
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26))+65)); // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10))); // 0~9
                    break;
            }
        }
        return key.toString();
    }

    public String sendSimpleMessage(String receiver) throws Exception {

        MimeMessage message = createMessage(receiver);
        try{//예외처리
            mailSender.send(message);
        }catch(MailException e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        return key;
    }
}
