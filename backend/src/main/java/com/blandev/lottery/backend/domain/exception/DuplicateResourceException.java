package com.blandev.lottery.backend.domain.exception;

public class DuplicateResourceException extends DomainException {
  public DuplicateResourceException(String message) {
    super(message);
  }

  public DuplicateResourceException(String resource, String identifier, String value) {
    super(String.format("%s with %s '%s' already exists", resource, identifier, value));
  }
}
