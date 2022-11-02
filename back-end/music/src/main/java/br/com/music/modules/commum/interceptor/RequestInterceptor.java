// package br.com.music.modules.commum.interceptor;
//
//
// import br.com.music.modules.commum.utils.UserInfo;
// import br.com.music.modules.user.usecase.UserUseCase;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerInterceptor;
//
// @Component
// @RequiredArgsConstructor
// public class RequestInterceptor implements HandlerInterceptor {
//
//  private final UserUseCase userUseCase;
//  private final UserInfo userInfo;
//
//  @Override
//  public boolean preHandle(
//      final HttpServletRequest req, final HttpServletResponse res, final Object handler) {
//
//    // final var email = JwtUtils.decodeJwt(req.getHeader(AUTHORIZATION_HEADER)).getEmail();
//    //    final var email = principal.getName();
//    //
//    //    final var user = userUseCase.findByEmail(email);
//    //
//    //    final var role =
//    // user.getRoles().stream().map(RoleDomain::getRoleName).findAny().orElse("");
//    //
//    //    userInfo.setUserId(user.getId());
//    //    userInfo.setUserIdMaster(user.getId_master() == null ? 0 : user.getId_master());
//    //    userInfo.setRole(role);
//    //    userInfo.setAdmin(role.equals(ADMIN.getRoleName()));
//
//    return true;
//  }
// }
