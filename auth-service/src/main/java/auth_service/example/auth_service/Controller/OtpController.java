package auth_service.example.auth_service.Controller;

import auth_service.example.auth_service.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class OtpController {
    @Autowired
    private OtpService otpService;

    @PostMapping("/send-otp")
    public String sendOtp(
            @RequestBody Map<String,String> request)
    {
        return otpService.sendOtp(
                request.get("email"));
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestBody Map<String,String> request)
    {
        return otpService.verifyOtp(
                request.get("email"),
                request.get("otp"));
    }
}
