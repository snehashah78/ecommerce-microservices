package auth_service.example.auth_service.Controller;
import auth_service.example.auth_service.repository.UserRepository;
import auth_service.example.auth_service.dto.LoginRequest;
import auth_service.example.auth_service.dto.LoginResponse;
import auth_service.example.auth_service.dto.SignupRequest;
import auth_service.example.auth_service.security.JwtUtil;
import auth_service.example.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/signup")
    public String signup(
            @RequestBody SignupRequest request) {

        return authService.signup(request);
    }


    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request)
    {

        String token =
                jwtUtil.generateToken(
                        request.getEmail());

        return new LoginResponse(token);
    }
    @GetMapping("/count")
    public Long getUserCount() {
        return userRepository.count();
    }
}
