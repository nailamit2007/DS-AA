package my.usecase.elevator;

import java.util.concurrent.PriorityBlockingQueue;

public class Elevator implements Runnable {

	public String direction;
	public int floor;
	public boolean free;
	public String liftName;
	public boolean isPoweDown = Boolean.FALSE;
	public String getLiftName() {
		return liftName;
	}
	public void setLiftName(String liftName) {
		this.liftName = liftName;
	}

	protected PriorityBlockingQueue<Person> liftUpReqqueue;
	protected PriorityBlockingQueue<Person> liftDownReqqueue;
	protected PriorityBlockingQueue<Person> liftInQueue;
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public Elevator(PriorityBlockingQueue<Person> liftqueue) {
		super();
		this.liftUpReqqueue = liftqueue;
	}
	public Elevator(String direction, int floor, boolean free, PriorityBlockingQueue<Person> liftqueue,PriorityBlockingQueue<Person> liftDownReqqueue, String liftName) {
		super();
		this.direction = direction;
		this.floor = floor;
		this.free = free;
		this.liftUpReqqueue = liftqueue;
		this.liftDownReqqueue = liftDownReqqueue;
		this.liftName = liftName;
	}
	@Override
	public void run() {
		liftInQueue = new PriorityBlockingQueue<Person>();
		try {
		while(!isPoweDown) {
			if(!liftUpReqqueue.isEmpty() || !liftInQueue.isEmpty() || !liftDownReqqueue.isEmpty()) {
					if(this.free) {
						System.out.println(this.liftName+" At floor "+this.getFloor());
						
						decideElevatorDirection();
						
						if(this.direction.equals("UP")) {
							checkEntryOrExit(liftUpReqqueue);
						}else {
							checkEntryOrExit(liftDownReqqueue);
						}
						
						if(this.direction.equals("UP")){
							moveUp();
						}else {
							moveDown();
						}
						
					}else {
						System.out.println(this.liftName+" Please wait. Lift in transit");
					}
			}else{
				
				System.out.println(this.liftName+" Lift is at halt.");
				Thread.sleep(60000);
			}
		}
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
	}
	private void decideElevatorDirection() {
	
		if(this.floor == 20){
			this.direction = "DOWN";
		}else if(this.floor == 0){
			this.direction = "UP";
		}
		
		synchronized (liftDownReqqueue) {
			
			if(this.direction.equals("UP")){
				if(liftInQueue.isEmpty() && liftUpReqqueue.isEmpty()) {
					Person person = liftDownReqqueue.peek();
					if(person != null && person.getFloor() == this.getFloor()) {
						this.direction = "DOWN";
					}
				}
			}
		}
		
		synchronized (liftUpReqqueue) {
			if(this.direction.equals("DOWN")){
				if(liftInQueue.isEmpty() && liftDownReqqueue.isEmpty()) {
					Person person = liftUpReqqueue.peek();
					if(person != null && person.getFloor() == this.getFloor()) {
						this.direction = "UP";
					}
				}
			}
		}
	}
	
	private void checkEntryOrExit(PriorityBlockingQueue<Person> liftReqqueue) throws InterruptedException {
		checkForExit();
		checkForEntry(liftReqqueue);
	}
	
	private void checkForEntry(PriorityBlockingQueue<Person> liftReqqueue) throws InterruptedException {
		Person person;
		int count = 0;
		count = 0;
		synchronized (liftReqqueue) {
			
			person = liftReqqueue.peek();
			//check for entry
			while(person != null && person.getFloor()==this.floor && person.getDirection().equals(this.direction)) {
				person = liftReqqueue.take();
				person.inLiftInd = Boolean.TRUE;
				liftInQueue.add(person);
				person = liftReqqueue.peek();
				count++;
			}
		}
		if(count>0) {
			System.out.println(this.liftName+" No Of persons "+ liftInQueue.size());
			System.out.println(this.liftName+" No Of persons entered "+ count);
		}
	}
	private void checkForExit() {
		int count = 0;
		Person personFrom;
		personFrom = liftInQueue.peek();
		//check for alighting
		while(personFrom!= null && personFrom.getDestination()==this.floor) {
			personFrom = (!liftInQueue.isEmpty())?liftInQueue.remove():null;
			personFrom = liftInQueue.peek();
			count++;
		}
		if(count>0) {
			System.out.println(this.liftName+" No Of persons "+ liftInQueue.size());
			System.out.println(this.liftName+" No Of persons alighted "+ count);
		}
	}
	
	private void moveUp() throws InterruptedException {
		System.out.println(this.liftName+" Lift moving to next floor");
		this.free = Boolean.FALSE;
		
		Thread.sleep(1000);
		
		this.floor = this.floor + 1;
		this.free = Boolean.TRUE;
	}
	
	private void moveDown() throws InterruptedException {
		System.out.println(this.liftName+" Lift moving to next floor");
		this.free = Boolean.FALSE;
		
		Thread.sleep(1000);
		
		this.floor = this.floor - 1;
		this.free = Boolean.TRUE;
	}
}
