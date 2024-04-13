package com.lms.logout;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class LogoutController {

    @Autowired
    private TokenBlacklist tokenBlacklist;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // Retrieve token from the request
        String token = extractTokenFromRequest(request);

        // Invalidate token by adding it to blacklist
        tokenBlacklist.blacklistToken(token);

        // Return a response indicating successful logout
        return ResponseEntity.ok("Logout successful");
    }

    // Method to extract token from the request
    private String extractTokenFromRequest(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Extract token from "Bearer <token>"
        }
        return null;
    }
}
