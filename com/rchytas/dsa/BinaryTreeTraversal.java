package com.rchytas.dsa;

//Java program for different tree traversals 

/* Class containing left and right child of current 
node and key value*/
class NodeThis 
{ 
	int key; 
	NodeThis left, right; 

	public NodeThis(int item) 
	{ 
		key = item; 
		left = right = null; 
	} 
} 

public class  BinaryTreeTraversal 
{ 
	// Root of Binary Tree 
	NodeThis root; 

	BinaryTreeTraversal() 
	{ 
		root = null; 
	} 

	/* Given a binary tree, print its nodes according to the 
	"bottom-up" postorder traversal. */
	void printPostorder(NodeThis node) 
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
	void printInorder(NodeThis node) 
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
	void printPreorder(NodeThis node) 
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

	/* function to print level order traversal of tree*/
    void printLevelOrder() 
    { 
        int h = height(root); 
        int i; 
        for (i=1; i<=h; i++) 
            printGivenLevel(root, i); 
    } 
  
    /* Compute the "height" of a tree -- the number of 
    nodes along the longest path from the root node 
    down to the farthest leaf node.*/
    int height(NodeThis root) 
    { 
        if (root == null) 
           return 0; 
        else
        { 
            /* compute  height of each subtree */
            int lheight = height(root.left); 
            int rheight = height(root.right); 
              
            /* use the larger one */
            if (lheight > rheight) 
                return(lheight+1); 
            else return(rheight+1);  
        } 
    } 
  
    /* Print nodes at the given level */
    void printGivenLevel (NodeThis root ,int level) 
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
	// Driver method 
	public static void main(String[] args) 
	{ 
		BinaryTreeTraversal tree = new BinaryTreeTraversal(); 
		tree.root = new NodeThis(1); 
		tree.root.left = new NodeThis(2); 
		tree.root.right = new NodeThis(3); 
		tree.root.left.left = new NodeThis(4); 
		tree.root.left.right = new NodeThis(5); 

		System.out.println("Preorder traversal of binary tree is "); 
		tree.printPreorder(); 

		System.out.println("\nInorder traversal of binary tree is "); 
		tree.printInorder(); 

		System.out.println("\nPostorder traversal of binary tree is "); 
		tree.printPostorder(); 
		
	    System.out.println("\nLevel order traversal of binary tree is "); 
		tree.printLevelOrder(); 
	} 
} 

/**
 * Sample output of above program is below -
 * 
 * 	Preorder traversal of binary tree is 
 *	1 2 4 5 3 
 *	Inorder traversal of binary tree is 
 *	4 2 5 1 3 
 *	Postorder traversal of binary tree is 
 *	4 5 2 3 1 
 *	Level order traversal of binary tree is 
 *	1 2 3 4 5 
 * 
**/