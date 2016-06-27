package com.elevatorsimulator.controller;

import java.util.Vector;

import com.elevatorsimulator.enumtype.ElevatorState;
import com.elevatorsimulator.pojo.Building;
import com.elevatorsimulator.pojo.Elevator;
import com.elevatorsimulator.pojo.Floor;
import com.elevatorsimulator.pojo.Person;
import com.elevatorsimulator.processor.Simulator;

public class ElevatorController {
	private Vector elevators;
	private Vector floors;
	private Logger log;



	private Elevator getNearestElevator(int floorNumber, int direction) {
		Elevator closestElevator = null;
		int closestFloor = 0;
		int closest = Building.MAX_FLOORS + 1;
		Elevator currentElevator = null;
		int currentFloorNumber = 0;
		for (int i = 0; i < elevators.size(); i++) {
			currentElevator = (Elevator) elevators.get(i);
			currentFloorNumber = currentElevator.getCurrentFloorNumber();
			if (direction == Elevator.MOVING_UP) { // summon up
				if (currentFloorNumber > closestFloor
						&& currentFloorNumber < floorNumber) {
					closestElevator = currentElevator;
					closestFloor = currentFloorNumber;
				}
			} else if (direction == Elevator.MOVING_DOWN) { // summon down
				if (currentFloorNumber < closestFloor
						&& currentFloorNumber > floorNumber) {
					closestElevator = currentElevator;
					closestFloor = currentFloorNumber;
				}
			} else if (direction == Elevator.NO_DIRECTION) { // get one close
																// but not on
																// the same
																// floor
				if (currentFloorNumber != floorNumber
						&& Math.abs(currentFloorNumber - floorNumber) < closest) {
					closestElevator = currentElevator;
					closest = Math.abs(currentFloorNumber - floorNumber);
				}
			}
		}
		return closestElevator;
	}

	private Elevator getElevator(int floorNumber, int direction) {
		Elevator closestElevator = null;
		int closestFloor = 0;
		int closest = Building.MAX_FLOORS + 1;
		Elevator currentElevator = null;
		int currentFloorNumber = 0;
		for (int i = 0; i < elevators.size(); i++) {
			currentElevator = (Elevator) elevators.get(i);
			currentFloorNumber = currentElevator.getCurrentFloorNumber();
			if (direction == Elevator.MOVING_UP) { // summon up
				if (currentFloorNumber > closestFloor
						&& currentFloorNumber < floorNumber) {
					closestElevator = currentElevator;
					closestFloor = currentFloorNumber;
				}
			} else if (direction == Elevator.MOVING_DOWN) { // summon down
				if (currentFloorNumber < closestFloor
						&& currentFloorNumber > floorNumber) {
					closestElevator = currentElevator;
					closestFloor = currentFloorNumber;
				}
			}
		}
		return closestElevator;
	}
}
