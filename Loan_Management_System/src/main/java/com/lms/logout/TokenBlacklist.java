package com.lms.logout;


import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class TokenBlacklist {

    private Set<String> blacklistedTokens;

    public TokenBlacklist() {
        this.blacklistedTokens = new HashSet<>();
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }

    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }

    public void removeTokenFromBlacklist(String token) {
        blacklistedTokens.remove(token);
    }
}
