package com.elevatorsimulator.pojo;

import java.util.Vector;

import com.elevatorsimulator.processor.Simulator;

public class Building {
	 public static  int MAX_FLOORS = 10;
	   public static  int MAX_ELEVATORS = 9;
	  // public ElevatorController elevatorController;
	   public  volatile int peopleTakingStairs;
	   public volatile int peopleOutside = Simulator.MAX_PEOPLE;
	   public volatile int peopleWorking;
	 

}
