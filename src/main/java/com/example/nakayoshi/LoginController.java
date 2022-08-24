package com.example.nakayoshi;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.SessionCookieOptions;

@Controller
public class LoginController {

  @RequestMapping(value = {"/", "/login"})
  public String login(){
    return "login";
  }

  @RequestMapping("/auth")
  @ResponseBody
  public String resCookie(HttpServletRequest request, HttpServletResponse response) throws FirebaseAuthException{
      String idToken = getToken(request);
      Cookie cookie = createCookie(idToken);

      response.addCookie(cookie);
      
      return "success"; 
  }

  private Cookie createCookie(String idToken) throws FirebaseAuthException{
    long expiresIn = TimeUnit.HOURS.toMillis(12);

    SessionCookieOptions options = SessionCookieOptions.builder()
        .setExpiresIn(expiresIn)
          .build(); 

    String sessionCookie = FirebaseAuth.getInstance().createSessionCookie(idToken, options);

    Cookie cookie = new Cookie("session", sessionCookie);

    cookie.setMaxAge(43200);
    cookie.setHttpOnly(true);
    cookie.setSecure(true);

    return cookie;
  } 

  // リクエストヘッダからトークンを取得
  private String getToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");

    if (token == null || !token.startsWith("Bearer ")) {
      return null;
    }

    return token.substring("Bearer ".length());
  }
}

