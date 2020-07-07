package com.rchytas.dsa;

import java.util.LinkedList;
import java.util.Queue;

//Java program for different tree traversals 

/* Class containing left and right child of current 
node and key value*/
class Node 
{ 
	int key; 
	Node left, right; 

	public Node(int item) 
	{ 
		key = item; 
		left = right = null; 
	} 
} 

public class  BinaryTreeTraversal 
{ 
	// Root of Binary Tree 
	Node root; 

	BinaryTreeTraversal() 
	{ 
		root = null; 
	} 

	/* Given a binary tree, print its nodes according to the 
	"bottom-up" postorder traversal. */
	void printPostorder(Node node) 
	{ 
		if (node == null) 
			return; 

		// first recur on left subtree 
		printPostorder(node.left); 

		// then recur on right subtree 
		printPostorder(node.right); 

		// now deal with the node 
		System.out.print(node.key + " "); 
	} 

	/* Given a binary tree, print its nodes in inorder*/
	void printInorder(Node node) 
	{ 
		if (node == null) 
			return; 

		/* first recur on left child */
		printInorder(node.left); 

		/* then print the data of node */
		System.out.print(node.key + " "); 

		/* now recur on right child */
		printInorder(node.right); 
	} 

	/* Given a binary tree, print its nodes in preorder*/
	void printPreorder(Node node) 
	{ 
		if (node == null) 
			return; 

		/* first print data of node */
		System.out.print(node.key + " "); 

		/* then recur on left sutree */
		printPreorder(node.left); 

		/* now recur on right subtree */
		printPreorder(node.right); 
	} 

	// Wrappers over above recursive functions 
	void printPostorder() {	 printPostorder(root); } 
	void printInorder() {	 printInorder(root); } 
	void printPreorder() {	 printPreorder(root); } 
	void printLeverorder() {	 printLevelOrder(root); } 

	/**
    void printLevelOrder() 
    { 
        int h = height(root); 
        int i; 
        for (i=1; i<=h; i++) 
            printGivenLevel(root, i); 
    } 
  
    int height(Node root) 
    { 
        if (root == null) 
           return 0; 
        else
        { 
            int lheight = height(root.left); 
            int rheight = height(root.right); 
              
            if (lheight > rheight) 
                return(lheight+1); 
            else return(rheight+1);  
        } 
    } 
  
    void printGivenLevel (Node root ,int level) 
    { 
        if (root == null) 
            return; 
        if (level == 1) 
            System.out.print(root.key + " "); 
        else if (level > 1) 
        { 
            printGivenLevel(root.left, level-1); 
            printGivenLevel(root.right, level-1); 
        } 
    } 
    **/
    void printLevelOrder(Node root){
    	if (root == null)
    		return;
    	
    	Queue<Node> q = new LinkedList<Node>();
    	
    	q.add(root);
    	
    	while (true) {
    		int nodeCount = q.size();
    		if (nodeCount == 0) break;
    		
    		while (nodeCount > 0) {
    			Node node = q.peek();   			
    			System.out.print(node.key + " ");
    			q.remove();
    			if (node.left != null) q.add(node.left);
    			if (node.right != null) q.add(node.right);
    			nodeCount--;
    		}
    		System.out.println();
    	}
    	
    	
    }
	// Driver method 
	public static void main(String[] args) 
	{ 
		BinaryTreeTraversal tree = new BinaryTreeTraversal(); 
		tree.root = new Node(1); 
		tree.root.left = new Node(2); 
		tree.root.right = new Node(3); 
		tree.root.left.left = new Node(4); 
		tree.root.left.right = new Node(5); 
		System.out.println("   1");
		System.out.println(" /   \\");
		System.out.println(" 2     3");
		System.out.println("/ \\");       
		System.out.println("4  5"); 

		System.out.println("Preorder (root * left * right) traversal of binary tree is  "); 
		tree.printPreorder();  

		System.out.println("\nInorder (left * root * right) traversal of binary tree is "); 
		tree.printInorder(); 

		System.out.println("\nPostorder (left * right * root) traversal of binary tree is "); 
		tree.printPostorder(); 
		
	    System.out.println("\nLevel (root * level1 * level2) order traversal of binary tree is "); 
		tree.printLeverorder(); 
	} 
} 

/**
 * Sample output of above program is below -
 *       1
 *      / \
 *     2   3
 *   /   \       
 *  4     5       
 *       
 *	Preorder (root * left * right) traversal of binary tree is  
 *	1 2 4 5 3 
 *	Inorder (left * root * right) traversal of binary tree is 
 *	4 2 5 1 3 
 *	Postorder (left * right * root) traversal of binary tree is 
 *	4 5 2 3 1 
 *	Level (root * level1 * level2) order traversal of binary tree is 
 *	1 
 *	2 3 
 *	4 5 
 * 
**/