package br.com.backend.backend.services;

import br.com.backend.backend.domains.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("backend") // Nome da aplicação, "criador"
                    .withSubject(user.getUsername()) // Usuário que está recebendo esse token
                    .withExpiresAt(generationDate())
                    .sign(algorithm); // Faz a assinatura e geração final

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro gerando o token do usuário: ", exception);
        }
    }

    public String validateToken(String token) {
        try {
            System.out.println("Entrou no try");
            Algorithm algorithm = Algorithm.HMAC256(secret);
            System.out.println("Algorithm: " + algorithm);
            return JWT.require(algorithm)
                    .withIssuer("backend") // Nome da aplicação, "criador"
                    .build()
                    .verify(token) // Descriptografa o token
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "Erro na válidação do token";
        }
    }

    private Instant generationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
