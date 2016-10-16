package my.utils;

public class BinarySearchTree {

	public Node root;
	
	class Node{
		int data;
		Node left;
		Node right;	
		public Node(int data){
			this.data = data;
			left = null;
			right = null;
		}
	}
	
	public boolean find(int id) {
		Node current = root;
		while(current != null) {
			if(current.data == id) {
				return true;
			}else if(current.data<id) {
				current = current.right;
			}else {
				current = current.left;
			}
		}
		return false;
	}
	
	/*public boolean delete(int id) {
		
	}*/
}
