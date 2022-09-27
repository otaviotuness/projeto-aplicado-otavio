package br.com.music.modules.commum.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RequiredArgsConstructor
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  private final JwtTokenStore tokenStore;
  // roles
  private static final String ADMIN = "ADMIN";
  private static final String MUSICIAN = "MUSICIAN";
  private static final String OPERATOR = "OPERATOR";

  private static final String ROLE_ADMIN = "ROLE_ADMIN";
  private static final String ROLE_MUSICIAN = "ROLE_MUSICIAN";
  private static final String ROLE_OPERATOR = "ROLE_OPERATOR";

  // public
  private static final String OAUTH_TOKEN = "/oauth/token";
  private static final String NEW_USER = "/newUser";
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

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.tokenStore(tokenStore);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        // public
        .antMatchers(OAUTH_TOKEN)
        .permitAll()
        .antMatchers(NEW_USER)
        .permitAll()
        .antMatchers(HttpMethod.GET, USER, USERS)
        .hasAnyRole(ADMIN)
        .antMatchers(HttpMethod.GET, SONG_BY_ID, SONGS)
        .hasAnyRole(ADMIN, MUSICIAN, OPERATOR)
        .antMatchers(HttpMethod.GET, CHECKLIST_BY_ID, CHECKLISTS, RECEIVES, RECEIVE_BY_ID)
        .hasAnyRole(ADMIN, MUSICIAN)
        .antMatchers(HttpMethod.POST, USER)
        .hasAnyRole(ADMIN)
        .antMatchers(HttpMethod.POST, SONG_BY_ID, CHECKLIST_BY_ID, RECEIVE_BY_ID)
        .hasAnyRole(ADMIN, MUSICIAN)
        .antMatchers(HttpMethod.DELETE, SONG_BY_ID, CHECKLIST_BY_ID, USER, RECEIVE_BY_ID)
        .hasAnyRole(ADMIN, MUSICIAN)
        // anyrequest
        .anyRequest()
        .denyAll();
  }
}
