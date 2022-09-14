package br.com.music.modules.interceptor;

import static br.com.music.modules.utils.BaseConstants.AUTHORIZATION_HEADER;

import br.com.music.modules.user.usecase.UserUseCase;
import br.com.music.modules.utils.UserInfo;
import br.com.music.modules.utils.jwt.JwtUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class InterceptorRequest implements HandlerInterceptor {

  private final UserUseCase userUseCase;
  private final UserInfo userInfo;

  @Override
  public boolean preHandle(
      final HttpServletRequest req, final HttpServletResponse res, final Object handler) {

    final var email = JwtUtils.decodeJwt(req.getHeader(AUTHORIZATION_HEADER)).getEmail();

    final var user = userUseCase.findByEmail(email);

    userInfo.setUserId(user.getId());
    userInfo.setUserIdMaster(user.getId_master());
    userInfo.setRole(user.getRoles().stream().findFirst().get().getRoleName());

    return true;
  }
}
