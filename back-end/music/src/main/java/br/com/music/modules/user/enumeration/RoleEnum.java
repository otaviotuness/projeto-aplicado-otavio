package br.com.music.modules.user.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {

    ADMIN(1,"ADMIN"),
    MUSICIAN(2, "MUSICIAN"),
    OPERATOR(3, "OPERATOR");

    private int id;
    private String roleName;
}
