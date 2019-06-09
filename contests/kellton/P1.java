package hiring.contests.kellton;

import java.util.Scanner;

public class P1 {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        
        int data[]= new int[n];
        long fwd[]= new long[n];
        long bwd[]= new long[n];
        
        for(int i=0; i<n; i++) {
        	data[i]= s.nextInt();
        }
        
        fwd[0]=data[0];
        for(int i=1; i<n; i++) {
        	fwd[i]= fwd[i-1] + data[i];
        }
        
        bwd[n-1] = data[n-1];
        for(int i=n-2; i>=0; i--) {
        	bwd[i]= bwd[i+1] + data[i];
        }
        
        long uper= fwd[0], neeche= bwd[0];
        for(int i=1; i<n; i++) {
        	if(data[i] != data[i-1]) {
        		if(uper == neeche) {
        			System.out.println("YES");
        			break;
        		} else {
        			uper = fwd[i];
        			neeche = bwd[i];
        		} 
        	} else {
    			neeche = bwd[i];
    		}
        }
	}

}
