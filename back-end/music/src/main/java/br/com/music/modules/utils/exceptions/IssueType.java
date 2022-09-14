package br.com.music.modules.utils.exceptions;

import java.util.IllegalFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IssueType {
  private static final Logger log = LoggerFactory.getLogger(IssueType.class);
  protected final String code;
  protected final String message;
  public static final IssueType UNEXPECTED_ERROR =
      new IssueType("0", "Unexpected error occurred, please contact the system administrator.");
  public static final IssueType BAD_REQUEST = new IssueType("01", "Invalid request.");
  public static final IssueType BAD_REQUEST_AUTHORIZATION_TOKEN_INVALID_OR_EXPIRED =
      new IssueType("02", "Authorization token is invalid or has expired.");
  public static final IssueType BAD_REQUEST_INVALID_JWT = new IssueType("03", "Invalid JWT.");

  public IssueType(String code, String message) {
    this.code = String.format("%s-%s", code);
    this.message = message;
  }

  public String getFormattedMessage(final Object... args) {
    if (this.message == null) {
      return "";
    } else {
      try {
        return String.format(this.message, args);
      } catch (IllegalFormatException var3) {
        log.warn(var3.getMessage(), var3);
        return this.message.replace("%s", "");
      }
    }
  }

  public String getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }
}
