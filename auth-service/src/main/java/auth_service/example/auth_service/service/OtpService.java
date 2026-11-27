package auth_service.example.auth_service.service;

import auth_service.example.auth_service.entity.Otp;
import auth_service.example.auth_service.entity.User;
import auth_service.example.auth_service.repository.OtpRepository;
import auth_service.example.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    public String sendOtp(String email) {

        String otp =
                String.valueOf(
                        100000 +
                                new Random().nextInt(900000)
                );

        Otp otpEntity = new Otp();

        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);

        otpRepository.save(otpEntity);

        emailService.sendOtpEmail(email, otp);

        return "OTP Sent Successfully";
    }

    public String verifyOtp(String email,
                            String enteredOtp) {

        Otp otp =
                otpRepository.findByEmail(email)
                        .orElseThrow();

        if(!otp.getOtp().equals(enteredOtp))
        {
            return "Invalid OTP";
        }

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow();

        user.setVerified(true);

        userRepository.save(user);

        otpRepository.delete(otp);

        return "OTP Verified";
    }
}
