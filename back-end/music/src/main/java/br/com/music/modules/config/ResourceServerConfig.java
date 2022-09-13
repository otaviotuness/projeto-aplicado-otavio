package br.com.music.modules.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
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

  private static final String[] PUBLIC = {"/oauth/token", "/newUser"};
  private static final String[] ADMIN = {
    "/user/**", "/users/**", "/event/**", "/role/**", "/song/{id}", "/songs", "/checklists"
  };
  private static final String[] MUSICIAN = {"/user/**", "/users/**", "/event/**"};
  private static final String[] OPERATOR = {
    "/event/**", "/song/{id}", "/songs",
  };

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.tokenStore(tokenStore);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(PUBLIC)
        .permitAll()
        .antMatchers(ADMIN)
        .hasAuthority("ROLE_ADMIN")
        .antMatchers(MUSICIAN)
        .hasAuthority("ROLE_MUSICIAN")
        .antMatchers(OPERATOR)
        .hasAuthority("ROLE_OPERATOR")
        .anyRequest()
        .denyAll();
  }
}
