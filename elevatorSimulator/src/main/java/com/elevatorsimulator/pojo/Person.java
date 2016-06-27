package com.elevatorsimulator.pojo;

import com.elevatorsimulator.common.Logger;
import com.elevatorsimulator.enumtype.PersonState;
import com.elevatorsimulator.exception.DoorClosedException;
import com.elevatorsimulator.exception.ElevatorFullException;
import com.elevatorsimulator.exception.ElevatorMovingException;
import com.elevatorsimulator.processor.Simulator;
/**
 * This represent Person
 * @author Srini
 *
 */
public class Person implements Runnable{
	   //possible actions
	   public static final int WAITING = 1; 
	   public static final int TAKING_STAIRS = 2; 
	   public static final int WORKING = 3;
	   public static final int WALKING_OUTSIDE = 4;
	   public static final int RIDING = 5;
	   public static final int GOING_NOWHERE = -1;
	   public static final int OUTSIDE = -1;
	   public static final int IN_ELEVATOR = 0;
	   private static Building building;
	   private int personID; 
	   private int destination;
	   private int location; //anything greater than zero is a floor number
	   private long activityTime; 
	   private int activity; 
	   private Elevator elevator; 
	   private Floor floor;
	   private Thread activePerson;
	   private Logger log;
	   private volatile boolean keepRunning;
	   private PersonState state = new PersonState();
	   public static void setBuilding(Building theBuilding){
	      building = theBuilding;
	   }
	   public Person(int personID){
	      this.personID = personID;
	   }
	   public void setStopRunning(){
	      keepRunning = false;
	   }
	   public boolean getKeepRunning(){
	      return keepRunning;
	   }
	   public synchronized void attention(){
	      activePerson.interrupt();
	   }
	   public synchronized void elevatorArrived(Elevator elevator) {        
	      this.elevator = elevator;
	   }


}
