package auth_service.example.auth_service.service;

import auth_service.example.auth_service.dto.SignupRequest;
import auth_service.example.auth_service.entity.User;
import auth_service.example.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    public String signup(SignupRequest request) {

        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already registered";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setVerified(false);

        userRepository.save(user);
        otpService.sendOtp(user.getEmail());
        return "User Registered Successfully";
    }
}
