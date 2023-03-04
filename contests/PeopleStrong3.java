package hiring.contest;

import java.util.Scanner;

public class PeopleStrong3 {
	
	private static int MOD= 1000_000_009;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s=new Scanner(System.in);
		int test=s.nextInt();
		
		while(test--!=0) {
			int n=s.nextInt(), a[]=new int[n];
			for(int i=0; i<n; i++) a[i]=s.nextInt();

			Node root=constructBST(a, n);
			//printBST(root);
			System.out.println(countPermutations(root));
		}
	}
	
	static void printBST(Node root) {
		if(root==null) return;
		printBST(root.left);
		System.out.println(root.val+ " "+ root.tleft+ " " + root.tright);
		printBST(root.right);
	}
	static int factorial(long x) {
		return (int) ((x <= 1) ? 1 : (x%MOD * factorial(x - 1)%MOD)%MOD);
	}

	static int f(long a, long b) {
		return factorial(a + b)%MOD / ((factorial(a)%MOD) * (factorial(b)%MOD))%MOD;
	}
	public static int countPermutations(Node root) {
		if(root.left==null && root.right==null) {
			return 1;
		} else if(root.left==null) {
			return countPermutations(root.right);
		} else if(root.right==null) {
			return countPermutations(root.left);
		} else {
			return (countPermutations(root.left)*countPermutations(root.right)*nCrModpDP(root.tleft+root.tright, root.tleft, MOD))%MOD;
		}
	}
	public static Node constructBST(int[] a, int n) {
		Node root=null;
		for(int i=0; i<n; i++) 
			root=insertNode(root, a[i]);

		return root;
	}
	public static Node insertNode(Node root, int data) {
		if(root==null) {
			return new Node(null, null, data);
		}
		else if(root.val<data) {
			root.right=insertNode(root.right, data);
			root.tright++;
		} else {
			root.left=insertNode(root.left, data);
			root.tleft++;
		}
		return root;

	}
	static int nCrModpDP(int n, int r, int p)
	{
	    
	    int C[]=new int[r+1];
	   
	 
	    C[0] = 1; 
	 
	    for (int i = 1; i <= n; i++)
	    {
	        
	        for (int j = Math.min(i, r); j > 0; j--)
	 
	            
	            C[j] = (C[j] + C[j-1])%p;
	    }
	    return C[r];
	}
	 

}

class Node {
	int val, tleft, tright;
	Node left, right;
	public Node(Node left, Node right, int val) {
		this.val=val;
		this.left=left;
		this.right=right;
		tleft=0;tright=0;
	}
}