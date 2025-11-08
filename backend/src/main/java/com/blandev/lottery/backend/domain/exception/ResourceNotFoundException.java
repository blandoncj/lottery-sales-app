package com.blandev.lottery.backend.domain.exception;

public class ResourceNotFoundException extends DomainException {
  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String resource, Long id) {
    super(String.format("%s with id %d not found", resource, id));
  }
}
