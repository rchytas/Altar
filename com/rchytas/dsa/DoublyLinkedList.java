package com.rchytas.dsa;

import java.util.Scanner;



public class DoublyLinkedList<E> {
	
	Node<Integer> start;
	Node<Integer> end;

	private static Scanner sc;
	
	public static void main(String[] args) {
		
		sc = new Scanner(System.in);

		DoublyLinkedList<Integer> myDemoDoublyList = new DoublyLinkedList<>();
		char ch = 'Y';
		while (ch == 'Y' || ch == 'y') {
			if (ch == 'Y' || ch == 'y') {
				System.out.println("Enter data for the node : ");
				int d = sc.nextInt();
				myDemoDoublyList.addNode(d);
			}
			System.out.println("Press (Y/y) to add another node : ");
			ch = sc.next().charAt(0);
		}

		System.out.println("Your list after adding all the nodes ");
		
		myDemoDoublyList.display();

	}

	/**
	 * Adds node to the List at the end / tail
	 */
	public void addNode(int data) {

		Node<Integer> toAdd = new Node<>(data);

		if (isEmpty()) {
			start = toAdd;
			end = toAdd;
			return;
		}

		end.next = toAdd;
		toAdd.prev = end;
		end = toAdd;
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
			System.out.print("LL Node " + nodeIndex + " has data <-> " + temp.data + " ");
			temp = temp.next;
			nodeIndex++;
		}
	}
	/**
	 * The Node class for my doubly LinkedList
	 * 
	 * @author mpandit
	 * @param <E>
	 */
	public static class Node<E> {
		public E data;
		public Node<E> next;
		public Node<E> prev;

		public Node(E data) {
			this.data = data;
			next = null;
			prev = null;
		}
	}
}
