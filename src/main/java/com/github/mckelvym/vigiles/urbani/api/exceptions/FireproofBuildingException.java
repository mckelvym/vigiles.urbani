package com.github.mckelvym.vigiles.urbani.api.exceptions;

public class FireproofBuildingException extends Exception {
  public FireproofBuildingException() {
    super("This building is fireproof and cannot be set on fire");
  }
}
