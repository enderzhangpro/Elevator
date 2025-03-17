package com.yourcompany;

/**
 * Custom exception thrown when code tries to move elevator to impossible floor.
 */
public class FloorOutOfBoundsException extends Exception {
	public FloorOutOfBoundsException() {
		super("Error: tries to move to impossible floor");
	}
}