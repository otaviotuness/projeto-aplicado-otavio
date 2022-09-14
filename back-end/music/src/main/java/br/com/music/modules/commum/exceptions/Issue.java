package br.com.music.modules.commum.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import org.springframework.http.HttpStatus;

@JsonInclude(Include.NON_NULL)
public class Issue implements Serializable {
  private static final long serialVersionUID = 1L;
  @JsonIgnore private HttpStatus httpStatus;
  @JsonProperty private String code;
  @JsonProperty private String message;
  @JsonProperty private List<String> details;

  public Issue(final IssueType issue) {
    this.httpStatus = HttpStatus.BAD_REQUEST;
    this.code = issue.getCode();
    this.message = issue.getFormattedMessage(new Object[0]);
  }

  public Issue(final IssueType issue, final List<String> details) {
    this(issue);
    this.details = details;
  }

  public Issue(final IssueType issue, final List<String> details, final Object... args) {
    this.httpStatus = HttpStatus.BAD_REQUEST;
    this.details = details;
    this.code = issue.getCode();
    this.message = issue.getFormattedMessage(args);
  }

  public Issue(final IssueType issue, final Object... args) {
    this.httpStatus = HttpStatus.BAD_REQUEST;
    this.code = issue.getCode();
    this.message = issue.getFormattedMessage(args);
  }

  public Issue(final IssueType issue, final HttpStatus httpStatus, final Object... args) {
    this.httpStatus = HttpStatus.BAD_REQUEST;
    this.code = issue.getCode();
    this.message = issue.getFormattedMessage(args);
    this.httpStatus = httpStatus;
  }

  public String toString() {
    return String.format(
        "Issue{code= %s, message='%s' details= '%s'}", this.code, this.message, this.details);
  }

  public HttpStatus getHttpStatus() {
    return this.httpStatus;
  }

  public String getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }

  public List<String> getDetails() {
    return this.details;
  }

  public Issue() {
    this.httpStatus = HttpStatus.BAD_REQUEST;
  }
}
