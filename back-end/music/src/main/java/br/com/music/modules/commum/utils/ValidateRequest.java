package br.com.music.modules.commum.utils;

import static br.com.music.modules.user.enumeration.RoleEnum.MUSICIAN;

import br.com.music.modules.commum.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateRequest {

  private final UserInfo userInfo;

  public void validate(final Integer idUser, final Integer idUserMater) {

    if (userInfo.getRole().contains(MUSICIAN.getRoleName())) {
      validate(idUserMater);
    } else {
      validate(idUser);
    }
  }

  public void validate(final Integer id) {

    if (userInfo.isAdmin()) return;

    if ((!id.equals(userInfo.getUserId())) && (!id.equals(userInfo.getUserIdMaster()))) {
      throw BadRequestException.notAuthorized();
    }
  }
}
