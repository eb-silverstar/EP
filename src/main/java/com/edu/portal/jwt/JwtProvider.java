package com.edu.portal.jwt;

import com.edu.portal.common.ApiException;
import com.edu.portal.common.Constants;
import com.edu.portal.tenant.TenantContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

@Component
public class JwtProvider {

    private static final long EXPIRATIONTIME = 1000L * 60 * 60 * 24 * 60;
    private static final String SIGNINGKEY = "!KtALp23hA!@Ed%^&u";

    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .claim("mbr_id", authentication.getName())
                .setAudience(TenantContext.getTenant())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS256, SIGNINGKEY)
                .compact();
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SIGNINGKEY)
                    .parseClaimsJws(request.getHeader("x-access-token"))
                    .getBody();

            return new UsernamePasswordAuthenticationToken(claims.get("mbr_id"), claims.get("mbr_pswd"), new ArrayList<>());

        } catch (Exception e) {
            throw new ApiException(Constants.INVALID_TOKEN);
        }
    }

    public static String getTenant(HttpServletRequest request) {
        String accessToken = request.getHeader("x-access-token");

        if(StringUtils.hasText(accessToken)) {
            return Jwts.parser()
                    .setSigningKey(SIGNINGKEY)
                    .parseClaimsJws(accessToken)
                    .getBody()
                    .getAudience();

        } else {
            return request.getHeader("x-tenant-id");
        }
    }

}
