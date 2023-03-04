package hiring.contest;

import java.util.Arrays;
import java.util.Scanner;

public class PayU1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		int t=s.nextInt();
		System.out.println(Math.log(4)/Math.log(2));
		
		while(t--!=0) {
			int n=s.nextInt();
			int[] a=new int[n];
			
			for(int i=0; i<n; i++) {
				a[i]=s.nextInt();
			}
			Arrays.sort(a);
			printArray(a);
			int j=a.length, cur=j;
			while(j!=0) {
				while(j>0 && a[j-1]==cur) {
					j--;
				}
				if(cur==j)
					break;
				else
					cur=j;
			}
			if(j!=0) 
				System.out.println("NO");
			else
				System.out.println("YES");
		}
	}
	static void printArray(int[] a) {
		for(int i=0; i<a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

}
