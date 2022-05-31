package com.github.mckelvym.vigiles.urbani.firefighters;

import static java.util.Objects.requireNonNull;

import com.github.mckelvym.vigiles.urbani.api.CityNode;
import com.github.mckelvym.vigiles.urbani.api.Firefighter;

/**
 * Immutable
 */
public class FirefighterImpl implements Firefighter {

	private final CityNode location;

	private final int distanceTraveled;

	public FirefighterImpl(CityNode location, int distanceTraveled) {
		this.location = requireNonNull(location);
		this.distanceTraveled = distanceTraveled;
	}

	@Override
	public CityNode getLocation() {
		return location;
	}

	@Override
	public int distanceTraveled() {
		return distanceTraveled;
	}
}
