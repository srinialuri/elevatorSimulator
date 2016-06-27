package com.elevatorsimulator.exception;

/**
 * Exception occurs when elevator is moving and person try to enter.
 * @author System
 *
 */
public class ElevatorMovingException extends Exception {

	private static final long serialVersionUID = -2095528203560186612L;
	
	public ElevatorMovingException() {
	}

}
