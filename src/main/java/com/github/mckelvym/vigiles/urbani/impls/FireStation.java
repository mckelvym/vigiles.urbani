package com.github.mckelvym.vigiles.urbani.impls;

import com.github.mckelvym.vigiles.urbani.api.CityNode;

public class FireStation extends BuildingImpl {

  public FireStation(CityNode location) {
    super(location, true);
  }
}
