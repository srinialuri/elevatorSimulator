package com.elevatorsimulator.pojo;

import java.util.Vector;

import com.elevatorsimulator.enumtype.ElevatorState;
import com.elevatorsimulator.processor.Simulator;
/**
 * This represent building
 * @author Srini
 *
 */
public class Elevator implements Runnable {
	 //Direction for motionDirection
	   public static final int MOVING_UP = 1;
	   public static final int NO_DIRECTION = 0;
	   public static final int MOVING_DOWN = -1;
	   //state values for motionState
	   public static final int MOVING = 1;
	   public static final int STOPPED = 0;
	   //state values for door
	   public static final int DOOR_OPEN = 1;
	   public static final int DOOR_CLOSED = 0;
	   private static final long FLOOR_WAIT_TIME = 1000; 
	   public static final long FLOOR_TRAVEL_TIME = 500;
	   private static final long INACTIVE_TIME = 1000 * 2;
	   private static final int MAX_OCCUPANCY = 20;
	   private int elevatorID; 
	   private int doorState; 
	   private int motionState;
	   private int motionDirection;
	   private volatile int currentFloorNumber;
	   private boolean requestDoorOpen;
	   private boolean[] destinationList = new boolean[Building.MAX_FLOORS]; // of type int
	   
	   private volatile boolean keepRunning;
	   private ElevatorState state = new ElevatorState();
	   public Elevator(int elevatorNumber){
	      elevatorID = elevatorNumber;
	      for(int i = 0; i < destinationList.length; i++){
	         destinationList[i] = false;
	      }
	      motionDirection = NO_DIRECTION;
	      currentFloorNumber = 1;
	      motionState = STOPPED;
	      doorState = DOOR_CLOSED;
	   }
	   
	   private synchronized void removeDestination(){
	      destinationList[currentFloorNumber - 1] = false;
	   }
	   private void stopElevator(){
	      motionState = STOPPED;
	   }
	   private synchronized boolean isDestination(){
	      boolean returnValue = false;
	      for(int i = 0; i < destinationList.length; i++){
	         if(destinationList[i]){
	            returnValue = true;
	            break;
	         }
	      }
	      return returnValue;
	   }

}
