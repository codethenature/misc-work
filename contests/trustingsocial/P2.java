package hiring.contests.trustingsocial;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P2 {
	public static void main(String args[] ) throws Exception {
		 Scanner s = new Scanner(System.in);
	        int test = s.nextInt();
	        
	        ArrayList<Integer> al = new ArrayList<>();
	        Prnew PriorityQueue<>();
	        while(test--!=0) {
	        	int type = s.nextInt();
	        	
	        	if(type == 1) { //add
	        		int temp = s.nextInt();
	        		al.add(temp);
	        	} else {
	        		System.out.println(( al.size()>=3 ) ? al.get(al.size()/3-1) : "Not enough enemies");
	        	}
	        }
	}
}
