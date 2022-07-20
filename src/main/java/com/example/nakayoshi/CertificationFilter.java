package com.example.nakayoshi;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.firebase.auth.FirebaseAuth;

@Component
public class CertificationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    // リクエストヘッダからJWTを取り出す
    String sessionToken = getSessionToken(request);

    try {
      // JWTを検証
      FirebaseAuth.getInstance().verifySessionCookie(sessionToken, true);

      filterChain.doFilter(request, response);

    // 検証に失敗
    } catch (Exception e) {
       
    }
  }

  // リクエストヘッダからトークンを取得
  private String getSessionToken(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();

    String sessionToken = ""; 
    for(int i = 0; i < cookies.length; i++){
      if(cookies[i].getName().equals("session")){
        sessionToken = cookies[i].getValue(); 
      }
    }
    return sessionToken;
  }

  @Bean
  public FilterRegistrationBean<CertificationFilter> filter(){
      FilterRegistrationBean<CertificationFilter> bean = new FilterRegistrationBean<>();

      bean.setFilter(new CertificationFilter());
      bean.addUrlPatterns("");

      return bean;
  } 
}
