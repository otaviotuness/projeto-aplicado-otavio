package br.com.music.modules.commum.utils;

import br.com.music.modules.commum.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateRequest {

  private final UserInfo userInfo;

  public void validate(final Integer id) {

    if (userInfo.isAdmin()) return;

    if ((!id.equals(userInfo.getUserId())) || (!id.equals(userInfo.getUserIdMaster()))) {
      throw BadRequestException.notAuthorized();
    }
  }
}
