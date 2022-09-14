package br.com.music.modules.commum.exceptions;

import java.util.List;

public class BadRequestException extends GlobalException {
  private static final long serialVersionUID = 1L;

  public BadRequestException(final Issue issue) {
    super(issue);
  }

  public static BadRequestException badRequest() {
    return new BadRequestException(new Issue(IssueType.BAD_REQUEST));
  }

  public static BadRequestException invalidParam(
      final IssueType issueType, final List<String> details) {
    return new BadRequestException(new Issue(issueType, details));
  }

  public static BadRequestException tokenInvalidOrExpired() {
    return new BadRequestException(
        new Issue(IssueType.BAD_REQUEST_AUTHORIZATION_TOKEN_INVALID_OR_EXPIRED));
  }

  public static BadRequestException notAuthorized() {
    return new BadRequestException(new Issue(IssueType.NOT_AUTHORIZED));
  }
}
