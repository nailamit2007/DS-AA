package my.usecase.elevator;

/**
 * The Class Person.
 */
public class Person implements Comparable<Person>{

	/** The direction. */
	public String direction;
	
	/** The floor. */
	public int floor;
	
	/** The destination. */
	public int destination;
	
	/** The in lift ind. */
	public Boolean inLiftInd = Boolean.FALSE;
	
	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}
	
	/**
	 * Sets the direction.
	 *
	 * @param direction the new direction
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	/**
	 * Gets the floor.
	 *
	 * @return the floor
	 */
	public int getFloor() {
		return floor;
	}
	
	/**
	 * Gets the destination.
	 *
	 * @return the destination
	 */
	public int getDestination() {
		return destination;
	}
	
	/**
	 * Sets the destination.
	 *
	 * @param destination the new destination
	 */
	public void setDestination(int destination) {
		this.destination = destination;
	}
	
	/**
	 * Instantiates a new person.
	 *
	 * @param direction the direction
	 * @param floor the floor
	 * @param destination the destination
	 */
	public Person(String direction, int floor, int destination) {
		super();
		this.direction = direction;
		this.floor = floor;
		this.destination = destination;
	}
	
	/**
	 * Sets the floor.
	 *
	 * @param floor the new floor
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
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
