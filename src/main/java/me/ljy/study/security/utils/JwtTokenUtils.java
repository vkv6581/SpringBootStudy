package me.ljy.study.security.utils;

import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ljy.study.user.util.UserRole;
import me.ljy.study.user.dto.User;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtTokenUtils {
    private static final String secretKey = "study_secretkey";      //token secret

    //유저 정보로 JWT 토큰 생성.
    public static String generateJwtToken(User user) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(user.getUserId())
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setExpiration(createExpireDate())
                .signWith(SignatureAlgorithm.HS256, createSigningKey());

        return builder.compact();
    }

    public static boolean isValidToken(String token) {
        try {
            Claims claims = getClaimsFormToken(token);
            return true;

        } catch (ExpiredJwtException exception) {
            //TODO : 에러 처리 필요
            return false;
        } catch (JwtException exception) {
            //TODO : 에러 처리 필요
            return false;
        } catch (NullPointerException exception) {
            //TODO : 에러 처리 필요
            return false;
        }
    }

    public static String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    private static Map<String, Object> createClaims(User user) {
        // 토큰에 담길 정보만 claims에 삽입.
        Map<String, Object> claims = new HashMap<>();

        claims.put("email", user.getEmail());
        claims.put("name", user.getName());
        claims.put("role", user.getRole());

        return claims;
    }

    private static Date createExpireDate() {
        // 토큰 만료시간은 30분으로 설정
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 30);
        return c.getTime();
    }

    private static Key createSigningKey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    private static Claims getClaimsFormToken(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token).getBody();
    }

    private static String getUserEmailFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return (String) claims.get("email");
    }

    private static String getNameFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return (String) claims.get("name");
    }

    private static UserRole getRoleFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return (UserRole) claims.get("role");
    }
}
