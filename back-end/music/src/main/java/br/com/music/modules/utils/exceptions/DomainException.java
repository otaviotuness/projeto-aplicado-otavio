package br.com.music.modules.utils.exceptions;

public class DomainException extends NoStackTraceException {

  protected DomainException(final String aMessage) {
    super(aMessage);
  }
}
