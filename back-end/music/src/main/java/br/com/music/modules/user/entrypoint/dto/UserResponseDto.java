package br.com.music.modules.user.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponseDto {

  private int id;
  private String name;
  private String email;
  private Set<String> roles = new HashSet<>();
  private String token;
}
