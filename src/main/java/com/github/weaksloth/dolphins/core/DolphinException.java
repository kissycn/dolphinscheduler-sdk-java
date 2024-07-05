package com.github.weaksloth.dolphins.core;

public class DolphinException extends RuntimeException {
  private int code;

  public DolphinException() {
    super();
  }

  public DolphinException(String message) {
    super(message);
  }

  public DolphinException(String message, Throwable e) {
    super(message, e);
  }

  public DolphinException(Integer code, String message) {
    super(message);
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
