package my.usecase.elevator;

import java.util.ArrayList;
import java.util.List;

public class Building {

	public int totFloors;
	public List<Elevator> noOfElevtors;
	public int getTotFloors() {
		return totFloors;
	}
	public void setTotFloors(int totFloors) {
		this.totFloors = totFloors;
	}
	
	public List<Elevator> getNoOfElevtors() {
		if(this.noOfElevtors!=null) {
			return noOfElevtors;
		}else{
			noOfElevtors = new ArrayList<Elevator>(); 
			return noOfElevtors;
		}
	}
	public void setNoOfElevtors(List<Elevator> noOfElevtors) {
		this.noOfElevtors = noOfElevtors;
	}
	public Building(int totFloors) {
		super();
		this.totFloors = totFloors;
	}
	
	public void runElevators() {
		for(Elevator elevator : noOfElevtors) {
			new Thread(elevator).start();
		}
	}
}
