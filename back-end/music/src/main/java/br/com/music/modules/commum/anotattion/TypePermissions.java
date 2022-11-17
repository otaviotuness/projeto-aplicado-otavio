package br.com.music.modules.commum.anotattion;

public final class TypePermissions {

  public static final String ADMIN = "SCOPE_ROLE_ADMIN";
  public static final String OPERATOR = "SCOPE_ROLE_OPERATOR";
  public static final String MUSICIAN = "SCOPE_ROLE_MUSICIAN";
  public static final String ADMIN_OPERATOR = ADMIN + "," + OPERATOR;
  public static final String ADMIN_MUSICIAN = ADMIN + "," + MUSICIAN;
  public static final String ALL = ADMIN + "," + MUSICIAN + "," + OPERATOR;
}
