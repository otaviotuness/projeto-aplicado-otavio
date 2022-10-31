package br.com.music.modules.commum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

  // roles
  private static final String ADMIN = "ADMIN";
  private static final String MUSICIAN = "MUSICIAN";
  private static final String OPERATOR = "OPERATOR";
  // public
  private static final String OAUTH_TOKEN = "/oauth/token";
  private static final String NEW_USER = "/newUser";
  private static final String LOGIN = "/login";
  // users
  private static final String USER = "/user/**";
  private static final String USERS = "/users/**";
  // song
  private static final String SONGS = "/songs/**";
  private static final String SONG_BY_ID = "/song/**";
  // checklsit
  private static final String CHECKLIST_BY_ID = "/checklist/**";
  private static final String CHECKLISTS = "/checklists/**";
  // receive
  private static final String RECEIVES = "/receives/**";
  private static final String RECEIVE_BY_ID = "/receive/**";
  // event
  private static final String EVENTS = "/events/**";
  private static final String EVENT_BY_ID = "/event/**";

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.httpBasic()
        .and()
        .authorizeHttpRequests()
        .antMatchers(OAUTH_TOKEN, LOGIN)
        .permitAll()
        .antMatchers(NEW_USER)
        .permitAll()
        .antMatchers(HttpMethod.GET, USER, USERS)
        .hasAnyRole(ADMIN)
        .antMatchers(HttpMethod.GET, SONG_BY_ID, SONGS, EVENTS, EVENT_BY_ID)
        .hasAnyRole(ADMIN, MUSICIAN, OPERATOR)
        .antMatchers(HttpMethod.GET, CHECKLIST_BY_ID, CHECKLISTS, RECEIVES, RECEIVE_BY_ID)
        .hasAnyRole(ADMIN, MUSICIAN)
        .antMatchers(HttpMethod.POST, USER)
        .hasAnyRole(ADMIN)
        .antMatchers(HttpMethod.POST, SONG_BY_ID, CHECKLIST_BY_ID, RECEIVE_BY_ID)
        .hasAnyRole(ADMIN, MUSICIAN)
        .antMatchers(
            HttpMethod.DELETE, SONG_BY_ID, CHECKLIST_BY_ID, USER, RECEIVE_BY_ID, EVENT_BY_ID)
        .hasAnyRole(ADMIN, MUSICIAN)
        .antMatchers(HttpMethod.POST, EVENT_BY_ID, EVENTS)
        .hasAnyRole(ADMIN, MUSICIAN, OPERATOR)
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable()
        .cors();
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
