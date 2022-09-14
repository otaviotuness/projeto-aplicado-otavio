package br.com.music.modules.commum.exceptions;

public class GlobalException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private final Issue issue;

  protected GlobalException(final Issue issue) {
    super(issue.getMessage());
    this.issue = issue;
  }

  protected GlobalException(final Issue issue, final Exception exception) {
    super(issue.getMessage(), exception);
    this.issue = issue;
  }

  public Issue getIssue() {
    return this.issue;
  }

  public static GlobalException globalException() {
    return new GlobalException(new Issue(IssueType.UNEXPECTED_ERROR));
  }
}
