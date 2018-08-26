package com.csrf.doublesubmitcookie.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class DoubleSubmitCookieService
{
    public boolean loginUser(String email, String password)
    {
        // Return true if login credentials are valid else return false
        return (email.equals("admin@gmail.com")) && (password.equals("admin"));
    }

    // Generate a unique random value
    public String generateUniqueId()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public HashMap saveToHashMap(String email, String password, String sessionId)
    {
        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("email", email);
        hashMap.put("password", password);
        hashMap.put("sessionId", sessionId);

        return hashMap;
    }
}
