package com.elevatorsimulator.pojo;

import java.util.Vector;

import com.elevatorsimulator.common.Logger;
import com.elevatorsimulator.controller.ElevatorController;
import com.elevatorsimulator.processor.Simulator;

public class Floor{
	 private int floorNumber;
	   private volatile boolean summonUp;
	   private volatile boolean summonDown;
	   private Vector upWaiting = new Vector(); // of type Person
	   private Vector downWaiting = new Vector(); // of type Person
	   private static ElevatorController elevatorController;
	   private Logger log;
	   public Floor(int floorNumber){
	      this.floorNumber = floorNumber;
	      if(Simulator.debug) log = new Logger("Floor" + this.floorNumber);
	   }
	   public void closeLog(){
	      if(log != null)log.close();
	   }
	   public boolean isSummonUp(){
	      return summonUp;
	   }
	   public boolean isSummonDown(){
	      return summonDown;
	   }
	   public static void setElevatorController(ElevatorController controller){
	      elevatorController = controller;
	   }
	   public int getFloorNumber(){
	      return floorNumber;
	   }
	   public int getNumberWaitingUp(){
	      return upWaiting.size();
	   }
	   public int getNumberWaitingDown(){
	      return downWaiting.size();
	   }
	   public void summonElevatorUp(Person person) {
	    
	   }
	   public void summonElevatorDown(Person person) {
	      
	   }      
	  
}
