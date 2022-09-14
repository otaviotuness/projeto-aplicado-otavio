package br.com.music.modules.commum.exceptions;

public class DomainException extends NoStackTraceException {

  protected DomainException(final String aMessage) {
    super(aMessage);
  }
}
