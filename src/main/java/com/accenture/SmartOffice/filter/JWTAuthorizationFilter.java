//package com.accenture.SmartOffice.filter;
//
//import com.accenture.SmartOffice.SecurityConstants;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//
//public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
//
//    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
//        super(authenticationManager, authenticationEntryPoint);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest req,
//                                    HttpServletResponse res,
//                                    FilterChain chain) throws IOException, ServletException {
//        String header = req.getHeader(SecurityConstants.HEADER_STRING);
//
//        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
//            chain.doFilter(req, res);
//            return;
//        }
//
//        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        chain.doFilter(req, res);
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
//        String token = request.getHeader(SecurityConstants.HEADER_STRING);
//        if (token != null) {
//            // parse the token.
//            Claims tokenClaims = Jwts.parser()
//                    .setSigningKey(SecurityConstants.SECRET.getBytes())
//                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX,""))
//                    .getBody();
//            Date experianceDate = tokenClaims.getExpiration();
//
//            if(experianceDate == null && experianceDate.before(new Date())) {
//                return null;
//            }
//
//            if (tokenClaims.getSubject() != null) {
//                return new UsernamePasswordAuthenticationToken(tokenClaims.getSubject(), null, new ArrayList<>());
//            }
//            return null;
//        }
//        return null;
//    }
//}
