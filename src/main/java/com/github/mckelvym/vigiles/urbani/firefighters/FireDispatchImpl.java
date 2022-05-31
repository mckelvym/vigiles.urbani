package com.github.mckelvym.vigiles.urbani.firefighters;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import com.github.mckelvym.vigiles.urbani.api.City;
import com.github.mckelvym.vigiles.urbani.api.CityNode;
import com.github.mckelvym.vigiles.urbani.api.FireDispatch;
import com.github.mckelvym.vigiles.urbani.api.Firefighter;
import com.github.mckelvym.vigiles.urbani.api.exceptions.NoFireFoundException;
import com.github.mckelvym.vigiles.urbani.api.exceptions.OutOfCityBoundsException;

/**
 * Implements {@link FireDispatch}
 *
 */
public class FireDispatchImpl implements FireDispatch {

	private final City city;

	private final List<Firefighter> firefighters;

	public FireDispatchImpl(City city) {
		this.city = requireNonNull(city);
		this.firefighters = new ArrayList<>();
	}

	@Override
	public void setFirefighters(int numFirefighters) {
		/**
		 * Reset state
		 */
		firefighters.clear();

		/**
		 * All start at fire station with no distance traveled
		 */
		CityNode fireStation = city.getFireStation().getLocation();
		for (int i = 0; i < numFirefighters; i++) {
			firefighters.add(new FirefighterImpl(fireStation, 0));
		}
	}

	@Override
	public List<Firefighter> getFirefighters() {
		/**
		 * Defensive copy, do not allow modification of internals if possible
		 */
		return List.copyOf(firefighters);
	}

	@Override
	public void dispatchFirefighers(CityNode... burningBuildings) {

		if (firefighters.isEmpty())
			return;

		/**
		 * Until all the burning buildings have been addressed...
		 */
		for (CityNode burningBuilding : burningBuildings) {
			/**
			 * Keep track of which fire fighter to send. This is based on both distance
			 * traveled if sent plus total distance traveled so far.
			 */
			int pickedIndex = 0;
			int distanceTotal = firefighters.get(pickedIndex).getLocation().distanceTo(burningBuilding)
					+ firefighters.get(pickedIndex).distanceTraveled();

			for (int ffIndex = 1; ffIndex < firefighters.size(); ffIndex++) {
				Firefighter ff = firefighters.get(ffIndex);
				int thisDistance = ff.getLocation().distanceTo(burningBuilding) + ff.distanceTraveled();
				if (thisDistance < distanceTotal) {
					pickedIndex = ffIndex;
				}
			}

			/**
			 * A fire fighter is guaranteed to be picked, so see how far the fire fighter
			 * must go.
			 */
			Firefighter ff = firefighters.get(pickedIndex);
			int distance = firefighters.get(pickedIndex).getLocation().distanceTo(burningBuilding);

			try {
				/**
				 * Put out the fire.
				 */
				city.getBuilding(burningBuilding).extinguishFire();

				/**
				 * Only if the fire is put out (no exception thrown), update fire fighter total
				 * distance travel with a new immutable instance
				 */
				firefighters.set(pickedIndex, new FirefighterImpl(burningBuilding, ff.distanceTraveled() + distance));
			} catch (OutOfCityBoundsException e) {
				e.printStackTrace();
			} catch (NoFireFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
