package my.usecase.elevator;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Building.
 */
public class Building {

	/** The tot floors. */
	public int totFloors;
	
	/** The no of elevtors. */
	public List<Elevator> noOfElevtors;
	
	/**
	 * Gets the tot floors.
	 *
	 * @return the tot floors
	 */
	public int getTotFloors() {
		return totFloors;
	}
	
	/**
	 * Sets the tot floors.
	 *
	 * @param totFloors the new tot floors
	 */
	public void setTotFloors(int totFloors) {
		this.totFloors = totFloors;
	}
	
	/**
	 * Gets the no of elevtors.
	 *
	 * @return the no of elevtors
	 */
	public List<Elevator> getNoOfElevtors() {
		if(this.noOfElevtors!=null) {
			return noOfElevtors;
		}else{
			noOfElevtors = new ArrayList<Elevator>(); 
			return noOfElevtors;
		}
	}
	
	/**
	 * Sets the no of elevtors.
	 *
	 * @param noOfElevtors the new no of elevtors
	 */
	public void setNoOfElevtors(List<Elevator> noOfElevtors) {
		this.noOfElevtors = noOfElevtors;
	}
	
	/**
	 * Instantiates a new building.
	 *
	 * @param totFloors the tot floors
	 */
	public Building(int totFloors) {
		super();
		this.totFloors = totFloors;
	}
	
	/**
	 * Run elevators.
	 */
	public void runElevators() {
		for(Elevator elevator : noOfElevtors) {
			new Thread(elevator).start();
		}
	}
}
