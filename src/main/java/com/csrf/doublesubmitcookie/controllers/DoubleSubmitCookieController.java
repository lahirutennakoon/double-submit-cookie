package com.csrf.doublesubmitcookie.controllers;

import com.csrf.doublesubmitcookie.services.DoubleSubmitCookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
public class DoubleSubmitCookieController
{
    // Create an object of the service class
    private DoubleSubmitCookieService doubleSubmitCookieService;

    private HashMap<String, String> hashMap;

    @Autowired
    DoubleSubmitCookieController(DoubleSubmitCookieService doubleSubmitCookieService)
    {
        this.doubleSubmitCookieService = doubleSubmitCookieService;
    }

    @PostMapping("/login")
    public RedirectView loginUser(@RequestParam String email, @RequestParam String password, HttpServletResponse response)
    {
        // Create cookie if login credentials are valid and return true
        if (this.doubleSubmitCookieService.loginUser(email,password))
        {
            // Generate sessionId and csrf token
            String sessionId = this.doubleSubmitCookieService.generateUniqueId();
            String csrfToken = this.doubleSubmitCookieService.generateUniqueId();

            // Save values to hashmap
            hashMap = this.doubleSubmitCookieService.saveToHashMap(email, password, sessionId);

            // Store sessionId and csrf token as cookies in the browser
            Cookie sessionIdCookie = new Cookie("sessionId", sessionId);
            Cookie csrfTokenCookie = new Cookie("csrfToken", csrfToken);

            response.addCookie(sessionIdCookie);
            response.addCookie(csrfTokenCookie);

            // Redirect to new page
            return new RedirectView("/form.html");
        }

        // Else redirect to same page with error message false
        return new RedirectView("/?loginStatus=invalid");
    }

    @PostMapping("/checkCsrfTokenValidity")
    public boolean checkCsrfTokenValidity(@RequestParam String sessionId, @RequestParam String csrfTokenInCookie,
                                          @RequestParam String csrfTokenInMessageBody)
    {
        String sessionIdFromHashMap = hashMap.get("sessionId");

        // Check if the session is valid
        if(sessionId.equals(sessionIdFromHashMap))
        {
            // Return true if the 2 csrf tokens match
            return csrfTokenInCookie.equals(csrfTokenInMessageBody);
        }

        // Return false if the 2 csrf tokens do not match
        return false;
    }
}
