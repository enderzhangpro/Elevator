package com.yourcompany;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test suite for elevator class. Tests all functionality of elevator.
 */
class ElevatorTest {
	
	private static Elevator simulator;

	// initializes the elevator variable for use
	@BeforeAll
	static void setUpBeforeClass() throws FloorOutOfBoundsException {
		simulator = new Elevator();
	}

	// resets the elevator between each test
	@BeforeEach
	void setUp() throws FloorOutOfBoundsException {
		simulator.setStartingFloor(0);
	}

	// makes sure starting floor is within bounds
	@Test
	void testStartingFloor() {
		assertThrows(FloorOutOfBoundsException.class, () -> simulator.setStartingFloor(-1));
		assertThrows(FloorOutOfBoundsException.class, () -> simulator.setStartingFloor(Elevator.TOP_FLOOR + 1));
		assertDoesNotThrow(() -> simulator.setStartingFloor(0));
		assertDoesNotThrow(() -> simulator.setStartingFloor(Elevator.TOP_FLOOR / 2));
		assertDoesNotThrow(() -> simulator.setStartingFloor(Elevator.TOP_FLOOR));
	}
	
	// makes sure elevator will never try to move to floor out of bounds
	@Test
	void testMoveBounds() {
		assertThrows(FloorOutOfBoundsException.class, () -> simulator.addFloor(-1));
		assertThrows(FloorOutOfBoundsException.class, () -> simulator.addFloor(Elevator.TOP_FLOOR + 1));
		assertDoesNotThrow(() -> simulator.addFloor(0));
		assertDoesNotThrow(() -> simulator.addFloor(Elevator.TOP_FLOOR / 2));
		assertDoesNotThrow(() -> simulator.addFloor(Elevator.TOP_FLOOR));
	}
	
	// makes sure elevator calculates total travel time correctly
	@Test
	void testTotalTravelTimeCalculations() throws FloorOutOfBoundsException {
		simulator.setStartingFloor(12);
		simulator.addFloor(2);
		simulator.addFloor(9);
		simulator.addFloor(1);
		simulator.addFloor(32);
		// Remember to move so that the elevator actually travels!
		for(int i = 0; i < 4; i ++) {
			simulator.move();
		}
		assertEquals(simulator.getTotalTravelTime(), 56 * Elevator.SINGLE_FLOOR_TRAVEL_TIME);
	}
}