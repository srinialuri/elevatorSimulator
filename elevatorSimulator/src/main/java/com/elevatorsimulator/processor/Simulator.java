package com.elevatorsimulator.processor;

import java.util.Vector;

import com.elevatorsimulator.pojo.Building;


/**
 * This represent Elevator Simulator
 * @author Srini 
 *
 */
public class Simulator {
	public static volatile boolean debug = false;
	   public static final int MAX_PEOPLE = 200;
	   public static final int MAX_TIME = 60;
	   private static volatile int counter;
	   public static void stopProgram(){
	      counter = MAX_TIME;
	   }
	   public static int getTimeRemaing(){
	       return (MAX_TIME - counter)/2;
	   }
	   public static int getElapsedTime(){
	       return counter/2;
	   }
	   public static void init(String noOfFloors,String noOfElevator)  {
	      //Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
	      String source = null;	    
	
	    
}
