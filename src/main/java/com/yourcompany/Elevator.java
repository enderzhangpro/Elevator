package com.yourcompany;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Elevator class that simulates using an elevator.
 * Will go to different floors and keep track of total time spent.
 */
public class Elevator {
	public static final int SINGLE_FLOOR_TRAVEL_TIME = 10;
	public static final int TOP_FLOOR = 100;
	private int floor;
	private int totalTravelTime;
	private ArrayList<Integer> floorsToVisit;
	
	// default constructor initializes starting floor to 0
	public Elevator() {
		this.floor = 0;
		this.totalTravelTime = 0;
		floorsToVisit = new ArrayList<>();
	}
	
	// to showcase polymorphism, accepts the starting floor as an argument
	public Elevator(int startingFloor) throws FloorOutOfBoundsException {
		if(startingFloor <= TOP_FLOOR && startingFloor >= 0) {
			this.floor = startingFloor;
			this.totalTravelTime = 0;
			this.floorsToVisit = new ArrayList<>();
		}
		else {
			throw new FloorOutOfBoundsException();
		}
	}
	
	// sets the starting floor of the elevator, but makes sure the starting floor is not out of bounds
	public void setStartingFloor(int startingFloor) throws FloorOutOfBoundsException {
		if(startingFloor <= TOP_FLOOR && startingFloor >= 0) {
			this.floor = startingFloor;
			this.totalTravelTime = 0; // resets travel time since this is supposed to be start of our journey
			this.floorsToVisit.clear();
		}
		else {
			throw new FloorOutOfBoundsException();
		}
	}
	
	// adds a floor to the list of floors to visit, again keeping everything within bounds
	public void addFloor(int nextFloor) throws FloorOutOfBoundsException {
		if(nextFloor <= TOP_FLOOR && nextFloor >= 0) {
			this.floorsToVisit.add(nextFloor);
		}
		else {
			throw new FloorOutOfBoundsException();
		}
	}
	
	// simulates elevator ascending and descending. Since earlier functions have handled bounds, this one doesn't have to.
	public void move() {
		if(this.floorsToVisit.size() > 0) {
			this.totalTravelTime += Math.abs(this.floorsToVisit.get(0) - this.floor) * SINGLE_FLOOR_TRAVEL_TIME;
			this.floor = this.floorsToVisit.get(0);
			this.floorsToVisit.remove(0);
		}
	}
	
	public int getFloor() {
		return this.floor;
	}
	
	public int getTotalTravelTime() {
		return this.totalTravelTime;
	}
	
	public static void main(String[] args) {
		Elevator simulator = null;
		int numFloorsToVisit = 0;
		String usageMessage = "usage: java Elevator.java [num] [comma-separated list of nums]";

		// Check for --help argument and print usage if present
		for(String arg : args) {
			if(arg.equals("--help") || arg.equals("-h")) {
				System.out.println(usageMessage);
				System.exit(0);
			}
		}
		
		// by caring about arguments, we can either run the program from command line or from user input
		if(args.length > 1) {
			int mostRecentFloor = 0;
			// if user doesn't input integer, don't crash and instead remind them how to use this program
			try {
				mostRecentFloor = Integer.parseInt(args[0]);
			}
			catch(NumberFormatException e) {
				System.out.println(usageMessage);
				System.exit(0);
			}
			try {
				simulator = new Elevator(mostRecentFloor);
			}
			catch(FloorOutOfBoundsException e) {
				System.out.println("Starting floor out of bounds. Must be between (0 and " + TOP_FLOOR + ")");
				System.exit(0);
			}
			String[] floors = null;
			// we also make sure not to crash if the user doesn't input only integers and commas
			try {
				floors = args[1].split(",");
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println(usageMessage);
				System.exit(0);
			}
			numFloorsToVisit = floors.length;
			// this boolean exists to make sure we only display the same floor warning message once per execution
			boolean displayedWarningMessage = false;
			for (int i = 0; i < floors.length; i++) {
				int nextFloor = 0;
				try {
					nextFloor = Integer.parseInt(floors[i].trim());
				}
				catch(NumberFormatException e) {
					System.out.println(usageMessage);
					System.exit(0);
				}
				// in this case, we can ignore this problem from command line and move on
				if(nextFloor == mostRecentFloor) {
					if(!displayedWarningMessage) {
						System.out.println("You are trying to go to the same floor twice. We'll ignore this for now, but just know that is not a thing");
						displayedWarningMessage = true;
					}
					// we must decrement this to avoid counting duplicates we won't be using
					numFloorsToVisit--;
					continue;
				}
				try {
					simulator.addFloor(nextFloor);
				}
				catch(FloorOutOfBoundsException e) {
					System.out.println("Floor " + nextFloor + " is out of bounds. Must be between (0 and " + TOP_FLOOR + ")");
					System.exit(0);
				}
				mostRecentFloor = nextFloor;
            }
			// print this at the end so that it only prints if all of the arguments are correct
			System.out.println("Ding! You pressed the up/down call button, and now your elevator is here!");
		}
		else {
			System.out.println("Ding! You pressed the up/down call button, and now your elevator is here!");
			// get user input on starting floor, and next floors
			Scanner scanner = new Scanner(System.in);
			simulator = new Elevator();
			int mostRecentFloor = 0;
			while(true) {
				System.out.print("Enter starting floor: ");
				// catch non-integer inputs instead of crashing
				try {
					// tracks the most recent floor input so we don't input the same floor twice
					mostRecentFloor = scanner.nextInt();
					scanner.nextLine(); // Consume the newline character
				}
				catch(InputMismatchException e) {
					System.out.println("Only integers count as valid input.");
					scanner.nextLine(); // Clear the invalid input
					continue;
				}
				// create a loop if user inputs invalid starting floors so that we don't have to exit immediately
				try {
					simulator.setStartingFloor(mostRecentFloor);
				}
				catch(FloorOutOfBoundsException e) {
					System.out.println("That floor is out of bounds.");
					continue;
				}
				break;
			}
			// get as many floors to visit as the user wants
			while(true) {
				System.out.print("Enter next floor to visit (or a negative number to stop): ");
				int nextFloor = 0;
				// catch non-integer inputs instead of crashing
				try {
					nextFloor = scanner.nextInt();
					scanner.nextLine(); // Consume the newline character
				}
				catch(InputMismatchException e) {
					System.out.println("Only integers count as valid input.");
					scanner.nextLine(); // Clear the invalid input
					continue;
				}
				if(nextFloor < 0) {
					break;
				}
				else if(nextFloor == mostRecentFloor) {
					// Nobody uses the elevator to go to the same floor
					System.out.println("Try going to a different floor!");
				}
				else {
					// try to add floor if possible, but handle FloorOutOfBoundsException
					try {
						simulator.addFloor(nextFloor);
					}
					catch(FloorOutOfBoundsException e) {
						System.out.println("That floor is out of bounds!");
						continue;
					}
					mostRecentFloor = nextFloor;
					numFloorsToVisit++;
				}
			}
			scanner.close();
		}
		// regardless of how we got our input, our code to actually move the elevator remains the same
		System.out.println("You started at floor " + simulator.getFloor());
		for(int i = 0; i < numFloorsToVisit; i++) {
			simulator.move();
			System.out.println("Then you moved to floor " + simulator.getFloor());
		}
		
		System.out.println("You traveled for " + simulator.getTotalTravelTime() + " seconds in total");
	}
}