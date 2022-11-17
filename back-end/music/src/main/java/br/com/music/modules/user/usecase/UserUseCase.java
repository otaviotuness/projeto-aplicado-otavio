package br.com.music.modules.user.usecase;

import br.com.music.modules.user.domain.UserDomain;
import br.com.music.modules.user.usecase.gateway.UserDadosGateway;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserUseCase {

  private final UserDadosGateway userDadosGateway;
  private final PasswordEncoder passwordEncoder;
  private final JwtEncoder encoder;

  public void saveUser(UserDomain userDomain) {
    // encrypt password
    userDomain.setPassword(passwordEncoder.encode(userDomain.getPassword()));

    userDadosGateway.saveUser(userDomain);
  }

  public List<UserDomain> findAll() {
    return userDadosGateway.findAll();
  }

  public Optional<UserDomain> findById(Integer id) {
    return userDadosGateway.findById(id);
  }

  public void deleteUserById(Integer id) {
    userDadosGateway.deleteUserById(id);
  }

  public UserDomain findByEmail(String email) {
    return userDadosGateway.findByEmail(email);
  }

  public String generateToken(Authentication authentication) {
    final var userName = authentication.getName();

    String scope =
        authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

    JwtClaimsSet claims = getToken(scope, userName);

    return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

  public String refreshToken(Authentication authentication) {
    final var userName = authentication.getName();
    final var user = userDadosGateway.findByEmail(userName);

    String scope =
        user.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

    JwtClaimsSet claims = getToken(scope, userName);

    return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

  private JwtClaimsSet getToken(final String scope, final String name) {
    return JwtClaimsSet.builder()
        .issuer("self")
        .issuedAt(Instant.now())
        .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
        .subject(name)
        .claim("scope", scope)
        .build();
  }
}
