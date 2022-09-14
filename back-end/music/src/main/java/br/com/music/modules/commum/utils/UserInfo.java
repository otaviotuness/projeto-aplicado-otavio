package br.com.music.modules.commum.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {

  private int userId;
  private int userIdMaster;
  private String role;
}
