package com.github.mckelvym.vigiles.urbani.api.exceptions;

public class OutOfCityBoundsException extends RuntimeException {
  public OutOfCityBoundsException() {
    super("This node is out of bounds!");
  }
}
