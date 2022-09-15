package br.com.music.modules.commum.exceptions.handler;

import br.com.music.modules.commum.exceptions.BadRequestException;
import br.com.music.modules.commum.exceptions.Issue;
import br.com.music.modules.commum.exceptions.IssueType;
import br.com.music.modules.commum.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MusicExceptionHandler {

  @ExceptionHandler(value = NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Issue handleNotFoundException(final NotFoundException ex) {
    return new Issue(IssueType.BAD_REQUEST, ex);
  }

  @ExceptionHandler(value = BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Issue handleBadRequestException(final BadRequestException ex) {
    return new Issue(IssueType.BAD_REQUEST, ex);
  }
}
