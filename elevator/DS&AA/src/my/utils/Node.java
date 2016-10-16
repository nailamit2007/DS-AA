package my.utils;

public class Node<T> {

	private T data;
	private Node<T> next;
	private Node<T> head;

	public Node(T data, Node<T> next) {
		super();
		this.data = data;
		this.next = next;
	}
	
	public void addFirst(T item) {
		head = new Node<T>(item, head);
	}
	
	public void printAllNodes() {
		Node<T> start = head;
		while(start != null) {
			System.out.println(start.data);
			start = start.next;
		}
	}
	
	public void addLast(T item) {
		if(head == null) {
			addFirst(item);
		}else{
			Node<T> start = head;
			while(start.next != null){
				start = start.next;
			}
			start.next = new Node<T>(item, null);
		}
	}
	
	public void remove(T item) {
		if(head == null) throw new RuntimeException("cannot delete");

	   if(head.data.equals(item))
	   {
	      head = head.next;
	      return;
	   }

	   Node<T> cur  = head;
	   Node<T> prev = null;

	   while(cur != null && !cur.data.equals(item) )
	   {
	      prev = cur;
	      cur = cur.next;
	   }

	   if(cur == null) throw new RuntimeException("cannot delete");

	   //delete cur node
	   prev.next = cur.next;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node<Integer> node = new Node<Integer>(2, null);
		
		node.addFirst(3);
		node.addFirst(4);
		node.addLast(5);
		node.remove(3);
		node.printAllNodes();
	}
	
}
