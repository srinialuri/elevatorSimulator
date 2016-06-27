package com.elevatorsimulator.controller;

import java.util.Vector;

import com.elevatorsimulator.common.Logger;
import com.elevatorsimulator.enumtype.ElevatorState;
import com.elevatorsimulator.exception.ElevatorMovingException;
import com.elevatorsimulator.pojo.Building;
import com.elevatorsimulator.pojo.Elevator;
import com.elevatorsimulator.pojo.Floor;
import com.elevatorsimulator.pojo.Person;
import com.elevatorsimulator.processor.Simulator;

public class ElevatorController {
	private Vector elevators;
	private Vector floors;
	private Logger log;

	public ElevatorController(Vector floors, Vector elevators) {
		this.floors = floors;
		this.elevators = elevators;
		if (Simulator.debug)
			log = new Logger("Controller");
	}

	public int getNumberWaitingUp(int floorNumber) {
		return getFloor(floorNumber).getNumberWaitingUp();
	}

	public int getNumberWaitingDown(int floorNumber) {
		return getFloor(floorNumber).getNumberWaitingDown();
	}

	public ElevatorState getElevatorState(int elevatorNumber) {
		return ((Elevator) elevators.get(elevatorNumber - 1)).getState();
	}

	public void stopElevators() {
		for (int i = 0; i < elevators.size(); i++) {
			((Elevator) elevators.get(i)).setStopRunning();
		}
		for (int i = 0; i < floors.size(); i++) {
			((Floor) floors.get(i)).closeLog();
		}
		if (log != null)
			log.close();
	}

	public void summonElevatorUp(int floorNumber, Person person) {
		Elevator e = null;
		int counter = 0;
		while (e == null && person.getKeepRunning()) { // need this to stop
														// processes stuck in
														// this loop when
														// program stops
			if (Simulator.debug)
				log.write("" + ++counter
						+ " tries to summon up elevator to floor "
						+ floorNumber);
			e = getSameFloorElevator(floorNumber);
			if (e != null) {
				if (Simulator.debug)
					log.write("Setting up destination for elevator "
							+ e.getElevatorNumber() + " same floor "
							+ floorNumber);
				try {
					e.summonDestination(floorNumber);
				} catch (ElevatorMovingException mx) {
					if (Simulator.debug)
						log.write("Moving Exception up1: floor " + floorNumber
								+ " on elevator " + e.getState());
					e = null;
					continue;
				}
			} else {
				if (floorNumber > 1) { // there won't be any below floor 1
					if (Simulator.debug)
						log.write("looking for one moving up from below to floor "
								+ floorNumber);
					e = getNearestElevator(floorNumber, Elevator.MOVING_UP);
				}
				if (e != null) {
					if (Simulator.debug)
						log.write("Setting destination for elevator "
								+ e.getElevatorNumber() + " from  below floor "
								+ floorNumber);
					try {
						e.summonDestination(floorNumber);
					} catch (ElevatorMovingException mx) {
						if (Simulator.debug)
							log.write("Moving Exception up2: floor "
									+ floorNumber + " on elevator "
									+ e.getState());
						e = null;
						continue;
					}
				} else {
					if (Simulator.debug)
						log.write("Looking for closest stopped elevator for up floor "
								+ floorNumber);
					e = getNearestElevator(floorNumber, Elevator.NO_DIRECTION);
					if (e != null) {
						if (Simulator.debug)
							log.write("Setting destination for stopped elevator "
									+ e.getElevatorNumber()
									+ " for up floor "
									+ floorNumber);
						try {
							e.summonDestination(floorNumber);
						} catch (ElevatorMovingException mx) {
							if (Simulator.debug)
								log.write("Moving Exception up3: floor "
										+ floorNumber + " on elevator "
										+ e.getState());
							e = null;
							continue;
						}
					} else {
						if (Simulator.debug)
							log.write("Looking for elevator coming down "
									+ floorNumber);
						e = getElevator(floorNumber, Elevator.MOVING_DOWN);
						if (e != null) {
							if (Simulator.debug)
								log.write("Setting destination for moving down elevator "
										+ e.getElevatorNumber()
										+ " for floor "
										+ floorNumber);
							try {
								e.summonDestination(floorNumber);
							} catch (ElevatorMovingException mx) {
								if (Simulator.debug)
									log.write("Moving Exception up4: floor "
											+ floorNumber + " on elevator "
											+ e.getState());
								e = null;
								continue;
							}
						}// end null any
					}// end null nearest stopped
				} // end null nearest moving up
			}// end null same floor
			if (e == null) {
				try {
					Thread.currentThread().sleep(1000); // wait a second and try
														// again
				} catch (InterruptedException ix) {
					// intentionally left empty
				}
			}// end if null for sleep
		}// end while
	}

		public Floor getFloor(int floorNumber) {
		return (Floor) floors.get(floorNumber - 1);
	}

	public void startElevators() {
		for (int i = 0; i < elevators.size(); i++) {
			((Elevator) elevators.get(i)).start();
		}
	}

}
