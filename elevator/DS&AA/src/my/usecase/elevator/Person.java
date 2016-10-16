package my.usecase.elevator;

public class Person implements Comparable<Person>{

	public String direction;
	public int floor;
	public int destination;
	public Boolean inLiftInd = Boolean.FALSE;
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getFloor() {
		return floor;
	}
	public int getDestination() {
		return destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
	public Person(String direction, int floor, int destination) {
		super();
		this.direction = direction;
		this.floor = floor;
		this.destination = destination;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	@Override
	public int compareTo(Person o) {
		if(o.inLiftInd.equals(this.inLiftInd) && this.inLiftInd) {
			if((o.direction.equals(this.direction)) && direction.equals("UP")) {
				if((o.getDestination()) < this.getDestination()) {
					return 1;
				}else if(o.getDestination() > this.getDestination()) {
					return -1;
				}else {
					return 0;
				}
			}else if((o.direction.equals(this.direction)) && direction.equals("DOWN")) {
				if(o.getDestination() < this.getDestination()) {
					return -1;
				}else if(o.getDestination() > this.getDestination()) {
					return 1;
				}else {
					return 0;
				}
			}else {
				return 0;
			}
		}else if(o.inLiftInd.equals(this.inLiftInd) && !this.inLiftInd) {
			
			if((o.direction.equals(this.direction)) && direction.equals("UP")) {
				if((o.getFloor()) < this.getFloor()) {
					return 1;
				}else if(o.getFloor() > this.getFloor()) {
					return -1;
				}else {
					return 0;
				}
			}else if((o.direction.equals(this.direction)) && direction.equals("DOWN")) {
				if(o.getFloor() < this.getFloor()) {
					return -1;
				}else if(o.getFloor() > this.getFloor()) {
					return 1;
				}else {
					return 0;
				}
			}else {
				return 0;
			}
		}else{
			return 0;
		}
	}
}
