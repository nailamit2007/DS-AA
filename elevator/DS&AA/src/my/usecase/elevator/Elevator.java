/*
 * @Amit NAIK
 */
package my.usecase.elevator;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * The Class Elevator.
 */
public class Elevator implements Runnable {

	/** The direction. */
	private String direction;

	/** The floor. */
	private int floor;

	/** The free. */
	private boolean free;

	/** The lift name. */
	private String liftName;

	private final double OVER_WEIGHT = 500;

	/** The is powe down. */
	private boolean isPoweDown = Boolean.FALSE;

	private boolean isOverWeight = Boolean.FALSE;

	/** The lift up reqqueue. */
	protected PriorityBlockingQueue<Person> liftUpReqqueue;

	/** The lift down reqqueue. */
	protected PriorityBlockingQueue<Person> liftDownReqqueue;

	/** The lift in queue. */
	protected PriorityBlockingQueue<Person> liftInQueue;

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public boolean isPoweDown() {
		return isPoweDown;
	}

	public void setPoweDown(boolean isPoweDown) {
		this.isPoweDown = isPoweDown;
	}

	public boolean isOverWeight() {
		return isOverWeight;
	}

	public void setOverWeight(boolean isOverWeight) {
		this.isOverWeight = isOverWeight;
	}

	public PriorityBlockingQueue<Person> getLiftUpReqqueue() {
		return liftUpReqqueue;
	}

	public void setLiftUpReqqueue(PriorityBlockingQueue<Person> liftUpReqqueue) {
		this.liftUpReqqueue = liftUpReqqueue;
	}

	public PriorityBlockingQueue<Person> getLiftDownReqqueue() {
		return liftDownReqqueue;
	}

	public void setLiftDownReqqueue(PriorityBlockingQueue<Person> liftDownReqqueue) {
		this.liftDownReqqueue = liftDownReqqueue;
	}

	public PriorityBlockingQueue<Person> getLiftInQueue() {
		return liftInQueue;
	}

	public void setLiftInQueue(PriorityBlockingQueue<Person> liftInQueue) {
		this.liftInQueue = liftInQueue;
	}

	/**
	 * Gets the lift name.
	 *
	 * @return the lift name
	 */
	public String getLiftName() {
		return liftName;
	}

	/**
	 * Sets the lift name.
	 *
	 * @param liftName
	 *            the new lift name
	 */
	public void setLiftName(String liftName) {
		this.liftName = liftName;
	}

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
	 * @param direction
	 *            the new direction
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
	 * Sets the floor.
	 *
	 * @param floor
	 *            the new floor
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}

	/**
	 * Instantiates a new elevator.
	 *
	 * @param liftqueue
	 *            the liftqueue
	 */
	public Elevator(PriorityBlockingQueue<Person> liftqueue) {
		super();
		this.liftUpReqqueue = liftqueue;
	}

	/**
	 * Instantiates a new elevator.
	 *
	 * @param direction
	 *            the direction
	 * @param floor
	 *            the floor
	 * @param free
	 *            the free
	 * @param liftqueue
	 *            the liftqueue
	 * @param liftDownReqqueue
	 *            the lift down reqqueue
	 * @param liftName
	 *            the lift name
	 */
	public Elevator(String direction, int floor, boolean free, PriorityBlockingQueue<Person> liftqueue,
			PriorityBlockingQueue<Person> liftDownReqqueue, String liftName) {
		super();
		this.direction = direction;
		this.floor = floor;
		this.free = free;
		this.liftUpReqqueue = liftqueue;
		this.liftDownReqqueue = liftDownReqqueue;
		this.liftName = liftName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		liftInQueue = new PriorityBlockingQueue<Person>();
		try {
			while (!isPoweDown) {
				if (!liftUpReqqueue.isEmpty() || !liftInQueue.isEmpty() || !liftDownReqqueue.isEmpty()) {
					if (this.free) {
						System.out.println(this.liftName + " At floor " + this.getFloor());

						decideElevatorDirection();

						if (this.direction.equals("UP")) {
							checkEntryOrExit(liftUpReqqueue);
						} else {
							checkEntryOrExit(liftDownReqqueue);
						}

						if (this.direction.equals("UP")) {
							moveUp();
						} else {
							moveDown();
						}

					} else {
						System.out.println(this.liftName + " Please wait. Lift in transit");
					}
				} else {

					System.out.println(this.liftName + " Lift is at halt.");
					Thread.sleep(60000);
				}
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	private double getCurrentWeight() {
		double currentWght = 0.0;
		for (Person person : liftInQueue) {
			currentWght += person.getWeight();
		}
		return currentWght;
	}

	/**
	 * Decide elevator direction.
	 */
	private void decideElevatorDirection() {

		if (this.floor == 20) {
			this.direction = "DOWN";
		} else if (this.floor == 0) {
			this.direction = "UP";
		}

		synchronized (liftDownReqqueue) {

			if (this.direction.equals("UP")) {
				if (liftInQueue.isEmpty() && liftUpReqqueue.isEmpty()) {
					Person person = liftDownReqqueue.peek();
					if (person != null && person.getFloor() == this.getFloor()) {
						this.direction = "DOWN";
					}
				}
			}
		}

		synchronized (liftUpReqqueue) {
			if (this.direction.equals("DOWN")) {
				if (liftInQueue.isEmpty() && liftDownReqqueue.isEmpty()) {
					Person person = liftUpReqqueue.peek();
					if (person != null && person.getFloor() == this.getFloor()) {
						this.direction = "UP";
					}
				}
			}
		}
	}

	/**
	 * Check entry or exit.
	 *
	 * @param liftReqqueue
	 *            the lift reqqueue
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private void checkEntryOrExit(PriorityBlockingQueue<Person> liftReqqueue) throws InterruptedException {
		checkForExit();
		checkForEntry(liftReqqueue);
	}

	/**
	 * Check for entry.
	 *
	 * @param liftReqqueue
	 *            the lift reqqueue
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private void checkForEntry(PriorityBlockingQueue<Person> liftReqqueue) throws InterruptedException {
		Person person;
		int count = 0;
		count = 0;
		
		double currentWght = getCurrentWeight();
		
		synchronized (liftReqqueue) {

			person = liftReqqueue.peek();
			// check for entry
			while (person != null && person.getFloor() == this.floor && person.getDirection().equals(this.direction)) {
				if(currentWght+person.getWeight() <= OVER_WEIGHT) {
					person = liftReqqueue.take();
					person.setInLiftInd(Boolean.TRUE);
					liftInQueue.add(person);
					currentWght = currentWght+person.getWeight();
				}else{
					System.out.println(this.liftName+" is OVERWEIGHT!!");
					System.out.println(this.liftName+" Last person entered has been stepped out.");
					break;
				}
				person = liftReqqueue.peek();
				count++;
			}
		}
		if (count > 0) {
			System.out.println(this.liftName + " No Of persons " + liftInQueue.size());
			System.out.println(this.liftName + " No Of persons entered " + count);
		}
	}

	/**
	 * Check for exit.
	 */
	private void checkForExit() {
		int count = 0;
		Person personFrom;
		personFrom = liftInQueue.peek();
		// check for alighting
		while (personFrom != null && personFrom.getDestination() == this.floor) {
			personFrom = (!liftInQueue.isEmpty()) ? liftInQueue.remove() : null;
			personFrom = liftInQueue.peek();
			count++;
		}
		if (count > 0) {
			System.out.println(this.liftName + " No Of persons " + liftInQueue.size());
			System.out.println(this.liftName + " No Of persons alighted " + count);
		}
	}

	/**
	 * Move up.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private void moveUp() throws InterruptedException {
		System.out.println(this.liftName + " Lift moving to next floor");
		this.free = Boolean.FALSE;

		Thread.sleep(1000);

		this.floor = this.floor + 1;
		this.free = Boolean.TRUE;
	}

	/**
	 * Move down.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private void moveDown() throws InterruptedException {
		System.out.println(this.liftName + " Lift moving to next floor");
		this.free = Boolean.FALSE;

		Thread.sleep(1000);

		this.floor = this.floor - 1;
		this.free = Boolean.TRUE;
	}
}
