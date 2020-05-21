package linkedLists;

import java.util.Scanner;

/**
 * Dynamic LinkedList Demo
 * 
 * Takes user input to build a LinkedList dynamically
 * 
 * Also, provides methods to insert node at specified position and delete a node
 * from specified position
 * 
 * @author mpandit
 * @param <E>
 *
 */
public class DynamicLinkedList<E> {

	Node<Integer> start;
	private static Scanner sc;

	public static void main(String[] args) {

		sc = new Scanner(System.in);

		DynamicLinkedList<Integer> myDemoList = new DynamicLinkedList<>();
		char ch = 'Y';
		while (ch == 'Y' || ch == 'y') {
			if (ch == 'Y' || ch == 'y') {
				System.out.println("Enter data for the node : ");
				int d = sc.nextInt();
				myDemoList.addNode(d);
			}
			System.out.println("Press (Y/y) to add another node : ");
			ch = sc.next().charAt(0);
		}

		System.out.println("Your list after adding all the nodes ");

		// Adding nodes done, now print the list
		myDemoList.display();

		System.out.println();

		// Now add a node at specified position
		System.out.println("Please enter the position where you want to add the node : ");
		int insertPos = sc.nextInt();

		System.out.println("Please enter data for new node : ");
		int data = sc.nextInt();

		myDemoList.insertNode(insertPos, data);

		// Inserting a node done, now print the list
		System.out.println("Your list after inserting a new node");

		myDemoList.display();

		System.out.println();

		// Now add a node at specified position
		System.out.println("Please enter the position from where you want to delete the node : ");
		int deletePos = sc.nextInt();
		myDemoList.deleteNode(deletePos);

		// Deleting a node done, now print the list
		System.out.println("Your list after deleting a node");

		myDemoList.display();

	}

	/**
	 * Adds node to the List at the end / tail
	 */
	public void addNode(int data) {

		Node<Integer> toAdd = new Node<>(data);

		if (isEmpty()) {
			start = toAdd;
			return;
		}

		Node<Integer> temp = start;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = toAdd;
		toAdd.next = null;
	}

	/**
	 * Returns if the List is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return start == null;
	}

	/**
	 * Displays the List
	 */
	public void display() {
		Node<Integer> temp = start;
		int nodeIndex = 1;
		while (temp != null) {
			System.out.print("LL Node " + nodeIndex + " has data -> " + temp.data + " ");
			temp = temp.next;
			nodeIndex++;
		}
	}

	/**
	 * Inserts node at particular position
	 * 
	 * @param position
	 */
	public void insertNode(int position, int data) {
		int i = 0;
		Node<Integer> temp = start;
		Node<Integer> old;
		while (i < position - 2) {
			temp = temp.next;
			i++;
		}
		old = temp.next;
		temp.next = new Node<>(data);
		temp = temp.next;
		temp.next = old;
	}

	/**
	 * Deletes a node from a position
	 * 
	 * @param position
	 */
	public void deleteNode(int position) {
		int i = 0;
		Node<Integer> temp = start;
		Node<Integer> old;
		while (i < position - 2) {
			temp = temp.next;
			i++;
		}
		old = temp.next;
		temp.next = old.next;
		temp = temp.next;
		old.next = null;
		old = null;

	}

	/**
	 * The Node class for my dynamic LinkedList
	 * 
	 * @author mpandit
	 * @param <E>
	 */
	public static class Node<E> {
		public E data;
		public Node<E> next;

		public Node(E data) {
			this.data = data;
			next = null;
		}
	}
}

/**
 *		A sample run of above program is below -
 
		Enter data for the node : 
		11
		Press (Y/y) to add another node : 
		y
		Enter data for the node : 
		22
		Press (Y/y) to add another node : 
		y
		Enter data for the node : 
		44
		Press (Y/y) to add another node : 
		y
		Enter data for the node : 
		55
		Press (Y/y) to add another node : 
		n
		LL Node 1 has data -> 11 LL Node 2 has data -> 22 LL Node 3 has data -> 44 LL Node 4 has data -> 55 
		Please enter the position where you want to add the node : 
		3
		Please enter data for new node : 
		33
		Your list after inserting a new node
		LL Node 1 has data -> 11 LL Node 2 has data -> 22 LL Node 3 has data -> 33 LL Node 4 has data -> 44 LL Node 5 has data -> 55 
		Please enter the position from where you want to delete the node : 
		4
		Your list after deleting a node
		LL Node 1 has data -> 11 LL Node 2 has data -> 22 LL Node 3 has data -> 33 LL Node 4 has data -> 55  
**/