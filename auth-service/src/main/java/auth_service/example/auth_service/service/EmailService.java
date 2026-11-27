package auth_service.example.auth_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

   // public void sendOtp(String email, String otp){
   public void sendOtpEmail(String email, String otp) {
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email);
        msg.setSubject("OTP Verification");
        msg.setText("Your OTP is : " + otp);
       System.out.println(otp);

        mailSender.send(msg);
    }


    }

