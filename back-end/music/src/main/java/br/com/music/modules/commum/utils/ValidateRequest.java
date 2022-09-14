package br.com.music.modules.commum.utils;

import br.com.music.modules.commum.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateRequest {

  private static UserInfo userInfo;

  public static void validate(final Integer id) {

    if (!id.equals(userInfo.getUserId()) || !id.equals(userInfo.getUserIdMaster())) {
      throw BadRequestException.notAuthorized();
    }
  }
}
