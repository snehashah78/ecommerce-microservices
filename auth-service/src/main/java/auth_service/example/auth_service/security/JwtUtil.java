package auth_service.example.auth_service.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET =
            "my-secret-key-my-secret-key-123456789";

    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 600000)
                )
                .signWith(
                        Keys.hmacShaKeyFor(SECRET.getBytes())
                )
                .compact();
    }
}
