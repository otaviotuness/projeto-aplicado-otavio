package br.com.music.modules.commum.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig implements WebMvcConfigurer {

  private final RsaKeyProperties rsaKeyProperties;

  // roles
  private static final String ADMIN = "ADMIN";
  private static final String MUSICIAN = "MUSICIAN";
  private static final String OPERATOR = "OPERATOR";
  // public
  private static final String OAUTH_TOKEN = "/token";
  private static final String NEW_USER = "/newUser";
  // users
  private static final String USER = "/user/**";
  private static final String USERS = "/users/**";
  private static final String USER_ME = "/me/**";
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

  //  @Bean
  //  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  //    http.httpBasic()
  //        .and()
  //        .authorizeHttpRequests()
  //        .antMatchers(OAUTH_TOKEN, LOGIN)
  //        .permitAll()
  //        .antMatchers(NEW_USER)
  //        .permitAll()
  //        .antMatchers(HttpMethod.GET, USER, USERS)
  //        .hasAnyRole(ADMIN)
  //        .antMatchers(HttpMethod.GET, SONG_BY_ID, SONGS, EVENTS, EVENT_BY_ID)
  //        .hasAnyRole(ADMIN, MUSICIAN, OPERATOR)
  //        .antMatchers(HttpMethod.GET, CHECKLIST_BY_ID, CHECKLISTS, RECEIVES, RECEIVE_BY_ID)
  //        .hasAnyRole(ADMIN, MUSICIAN)
  //        .antMatchers(HttpMethod.POST, USER)
  //        .hasAnyRole(ADMIN)
  //        .antMatchers(HttpMethod.POST, SONG_BY_ID, CHECKLIST_BY_ID, RECEIVE_BY_ID)
  //        .hasAnyRole(ADMIN, MUSICIAN)
  //        .antMatchers(
  //            HttpMethod.DELETE, SONG_BY_ID, CHECKLIST_BY_ID, USER, RECEIVE_BY_ID, EVENT_BY_ID)
  //        .hasAnyRole(ADMIN, MUSICIAN)
  //        .antMatchers(HttpMethod.POST, EVENT_BY_ID, EVENTS)
  //        .hasAnyRole(ADMIN, MUSICIAN, OPERATOR)
  //        .anyRequest()
  //        .authenticated()
  //        .and()
  //        .csrf()
  //        .disable()
  //        .cors();
  //    return http.build();
  //  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .authorizeRequests(
            auth -> auth.mvcMatchers("/token").permitAll().anyRequest().authenticated())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .exceptionHandling(
            (ex) ->
                ex.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                    .accessDeniedHandler(new BearerTokenAccessDeniedHandler()))
        .httpBasic(Customizer.withDefaults())
        .cors(AbstractHttpConfigurer::disable)
        .build();
  }

  @Bean
  JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(rsaKeyProperties.getPublicKey()).build();
  }

  @Bean
  JwtEncoder jwtEncoder() {
    JWK jwk =
        new RSAKey.Builder(rsaKeyProperties.getPublicKey())
            .privateKey(rsaKeyProperties.getPrivateKey())
            .build();
    JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
    return new NimbusJwtEncoder(jwks);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
