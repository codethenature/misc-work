package hiring.contest;

import java.util.Scanner;

public class Monocept2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		
		int x=s.nextInt();
		int n=s.nextInt();
		int[] a=new int[n];
		int[] b=new int[n];
		
		for(int i=0; i<n; i++) {
			a[i]=s.nextInt();
			b[i]=s.nextInt();
		}
		
		System.out.println(rec(a, b, 0, 0, x));
	}
	public static int rec(int[] a, int[] b, int A, int B, int x) {
		System.out.println(B+ " "+A);
		if(A==x)
			return B;
		
		int max=B;
		for(int i=0; i<a.length; i++) {
			if(A+a[i]<=x) {
				int temp=rec(a, b, A+a[i], B+b[i], x);
				if(max<temp)
					max=temp;
			}
			
		}
		return max;
		
	}

}
