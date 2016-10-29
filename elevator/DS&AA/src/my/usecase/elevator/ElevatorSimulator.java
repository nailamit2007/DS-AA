/*
 * @Amit NAIK
 */
package my.usecase.elevator;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * The Class ElevatorSimulator.
 */
public class ElevatorSimulator {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		PriorityBlockingQueue<Person> liftqueue = new PriorityBlockingQueue<Person>(20);
		PriorityBlockingQueue<Person> liftDownqueue = new PriorityBlockingQueue<Person>(20);
		Person person1 = new Person("UP", 0, 20, 50.0);
		Person person2 = new Person("UP", 15, 18, 89.0);
		Person person4= new Person("UP", 10, 20, 452.0);
		Person person3 = new Person("DOWN", 10, 2, 90.0);
		
		liftqueue.add(person1);
		liftqueue.add(person2);
		liftqueue.add(person4);
		liftDownqueue.add(person3);
		Elevator elevator1 = new Elevator("UP", 0, Boolean.TRUE, liftqueue, liftDownqueue, "Lift1");
		Elevator elevator2 = new Elevator("UP", 0, Boolean.TRUE, liftqueue, liftDownqueue, "Lift2");
		
		Building building = new Building(20);
		building.getNoOfElevtors().add(elevator1);
		building.getNoOfElevtors().add(elevator2);
		
		building.runElevators();
	}
}
