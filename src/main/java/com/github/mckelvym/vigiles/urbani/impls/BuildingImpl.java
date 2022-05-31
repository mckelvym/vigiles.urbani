package com.github.mckelvym.vigiles.urbani.impls;

import com.github.mckelvym.vigiles.urbani.api.Building;
import com.github.mckelvym.vigiles.urbani.api.CityNode;
import com.github.mckelvym.vigiles.urbani.api.exceptions.FireproofBuildingException;
import com.github.mckelvym.vigiles.urbani.api.exceptions.NoFireFoundException;

public class BuildingImpl implements Building {
  private final CityNode location;
  private boolean isBurning;
  private final boolean fireproof;

  public BuildingImpl(CityNode location) {
    this.location = location;
    this.fireproof = false;
    this.isBurning = false;
  }

  BuildingImpl(CityNode location, boolean fireproof) {
    this.location = location;
    this.fireproof = fireproof;
    this.isBurning = false;
  }

  @Override
  public CityNode getLocation() {
    return location;
  }

  @Override
  public boolean isBurning() {
    return isBurning;
  }

  @Override
  public boolean isFireproof() {
    return fireproof;
  }

  @Override
  public void extinguishFire() throws NoFireFoundException {
    if (isBurning) {
      this.isBurning = false;
    } else {
      throw new NoFireFoundException();
    }
  }

  @Override
  public void setFire() throws FireproofBuildingException {
    if (!fireproof) {
      this.isBurning = true;
    } else {
      throw new FireproofBuildingException();
    }
  }
}
