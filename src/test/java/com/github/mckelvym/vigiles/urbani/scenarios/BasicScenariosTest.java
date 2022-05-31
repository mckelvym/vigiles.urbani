package com.github.mckelvym.vigiles.urbani.scenarios;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.github.mckelvym.vigiles.urbani.api.City;
import com.github.mckelvym.vigiles.urbani.api.CityNode;
import com.github.mckelvym.vigiles.urbani.api.FireDispatch;
import com.github.mckelvym.vigiles.urbani.api.Firefighter;
import com.github.mckelvym.vigiles.urbani.api.Pyromaniac;
import com.github.mckelvym.vigiles.urbani.api.exceptions.FireproofBuildingException;
import com.github.mckelvym.vigiles.urbani.firefighters.FireDispatchImpl;
import com.github.mckelvym.vigiles.urbani.impls.CityImpl;

class BasicScenariosTest {

	@Test
	public void singleFire() throws FireproofBuildingException {
		City basicCity = new CityImpl(5, 5, new CityNode(0, 0));
		FireDispatch fireDispatch = basicCity.getFireDispatch();

		CityNode fireNode = new CityNode(0, 1);
		Pyromaniac.setFire(basicCity, fireNode);

		fireDispatch.setFirefighters(1);
		fireDispatch.dispatchFirefighers(fireNode);
		Assert.assertFalse(basicCity.getBuilding(fireNode).isBurning());
	}

	@Test
	public void singleFireDistanceTraveledDiagonal() throws FireproofBuildingException {
		City basicCity = new CityImpl(2, 2, new CityNode(0, 0));
		FireDispatch fireDispatch = basicCity.getFireDispatch();

		// Set fire on opposite corner from Fire Station
		CityNode fireNode = new CityNode(1, 1);
		Pyromaniac.setFire(basicCity, fireNode);

		fireDispatch.setFirefighters(1);
		fireDispatch.dispatchFirefighers(fireNode);

		Firefighter firefighter = fireDispatch.getFirefighters().get(0);
		Assert.assertEquals(2, firefighter.distanceTraveled());
		Assert.assertEquals(fireNode, firefighter.getLocation());
	}

	@Test
	public void singleFireDistanceTraveledAdjacent() throws FireproofBuildingException {
		City basicCity = new CityImpl(2, 2, new CityNode(0, 0));
		FireDispatch fireDispatch = basicCity.getFireDispatch();

		// Set fire on adjacent X position from Fire Station
		CityNode fireNode = new CityNode(1, 0);
		Pyromaniac.setFire(basicCity, fireNode);

		fireDispatch.setFirefighters(1);
		fireDispatch.dispatchFirefighers(fireNode);

		Firefighter firefighter = fireDispatch.getFirefighters().get(0);
		Assert.assertEquals(1, firefighter.distanceTraveled());
		Assert.assertEquals(fireNode, firefighter.getLocation());
	}

	@Test
	public void simpleDoubleFire() throws FireproofBuildingException {
		City basicCity = new CityImpl(2, 2, new CityNode(0, 0));
		FireDispatch fireDispatch = basicCity.getFireDispatch();

		CityNode[] fireNodes = { new CityNode(0, 1), new CityNode(1, 1) };
		Pyromaniac.setFires(basicCity, fireNodes);

		fireDispatch.setFirefighters(1);
		fireDispatch.dispatchFirefighers(fireNodes);

		Firefighter firefighter = fireDispatch.getFirefighters().get(0);
		Assert.assertEquals(2, firefighter.distanceTraveled());
		Assert.assertEquals(fireNodes[1], firefighter.getLocation());
		Assert.assertFalse(basicCity.getBuilding(fireNodes[0]).isBurning());
		Assert.assertFalse(basicCity.getBuilding(fireNodes[1]).isBurning());
	}

	@Test
	public void distanceCheck() {
		Assert.assertTrue(new CityNode(0, 0).distanceTo(new CityNode(5, 0)) == 5);
		Assert.assertTrue(new CityNode(0, 0).distanceTo(new CityNode(0, 5)) == 5);
		Assert.assertTrue(new CityNode(0, 0).distanceTo(new CityNode(5, 5)) == 10);
	}

	@Test
	public void doubleFirefighterDoubleFire() throws FireproofBuildingException {
		City basicCity = new CityImpl(2, 2, new CityNode(0, 0));
		FireDispatch fireDispatch = basicCity.getFireDispatch();

		CityNode[] fireNodes = { new CityNode(0, 1), new CityNode(1, 0) };
		Pyromaniac.setFires(basicCity, fireNodes);

		fireDispatch.setFirefighters(2);
		fireDispatch.dispatchFirefighers(fireNodes);

		List<Firefighter> firefighters = fireDispatch.getFirefighters();
		int totalDistanceTraveled = 0;
		boolean firefighterPresentAtFireOne = false;
		boolean firefighterPresentAtFireTwo = false;
		for (Firefighter firefighter : firefighters) {
			totalDistanceTraveled += firefighter.distanceTraveled();

			if (firefighter.getLocation().equals(fireNodes[0])) {
				firefighterPresentAtFireOne = true;
			}
			if (firefighter.getLocation().equals(fireNodes[1])) {
				firefighterPresentAtFireTwo = true;
			}
		}

		Assert.assertEquals(2, totalDistanceTraveled);
		Assert.assertTrue(firefighterPresentAtFireOne);
		Assert.assertTrue(firefighterPresentAtFireTwo);
		Assert.assertFalse(basicCity.getBuilding(fireNodes[0]).isBurning());
		Assert.assertFalse(basicCity.getBuilding(fireNodes[1]).isBurning());
	}
}
