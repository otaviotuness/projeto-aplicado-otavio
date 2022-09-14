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
  private static final String ADMIN = "ROLE_ADMIN";
  private static final String MUCISIAN = "ROLE_MUSICIAN";
  private static final String OPERATOR = "ROLE_OPERATOR";

  private static final String OAUTH_TOKEN = "/oauth/token";
  private static final String NEW_USER = "/newUser";
  private static final String USER = "/user/**";
  private static final String USERS = "/users/**";
  private static final String SONGS = "/songs/**";
  private static final String SONG_BY_ID = "/song/**";

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
        // admin
        .antMatchers(HttpMethod.GET, SONG_BY_ID)
        .hasAuthority(ADMIN)
        .antMatchers(HttpMethod.GET, SONGS)
        .hasAuthority(ADMIN)
        // anyrequest
        .anyRequest()
        .denyAll();
  }
}
