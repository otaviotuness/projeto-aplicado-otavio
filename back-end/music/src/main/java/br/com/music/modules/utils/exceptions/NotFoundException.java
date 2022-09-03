package br.com.music.modules.utils.exceptions;

public class NotFoundException extends DomainException {

  protected NotFoundException(final String aMessage) {
    super(aMessage);
  }

  public static NotFoundException with(final int id) {
    final var anError = String.format("ID %s was not found", id);
    return new NotFoundException(anError);
  }
}
