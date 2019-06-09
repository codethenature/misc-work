package hiring.contests.hotstar;

import java.util.Scanner;

public class P1 {
	
	private static int MOD = 1000000007;
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
        int t=s.nextInt();
        
        while(t--!=0) {
        	int n=s.nextInt();
        	int[] a=new int[n];
        	
        	for(int i=0; i<n; i++) {
        		a[i] = s.nextInt();
        	}
        	int start=0;
        	for(; start<n && a[start]==0; start++);
        	int len = n-start+1;
        	int parts = len%3==0 ? len/3 : len/3 + 1;
        	int sumr =0, i, count;
        	int summ =0, suml=0;
        	
        	for( i=n-1, count=0; count < parts ; i--, count++) {
        		sumr += (power(2, count, MOD)*a[i] )%MOD;
        		sumr%=MOD;
        	}
        	
        	for( count=0; count < parts ; i--, count++) {
        		summ += (power(2, count, MOD)*a[i] )%MOD;
        		summ%=MOD;
        	}
        	
        	for( count=0; i >= start ; count++,i--) {
        		suml += (power(2, count, MOD)*a[i] )%MOD;
        		suml%=MOD;
        	}
        	
        	if(suml==summ && suml==sumr) {
        		System.out.println(summ);
        	} else {
        		System.out.println(-1);
        	}
        }
	}
	
    public static int power(int x,  int y, int p)
{
    int res = 1;      // Initialize result
 
    x = x % p;  // Update x if it is more than or 
                // equal to p
 
    while (y > 0)
    {
        // If y is odd, multiply x with result
        if (y & 1)
            res = (res*x) % p;
 
        // y must be even now
        y = y>>1; // y = y/2
        x = (x*x) % p;  
    }
    return res;
}

}
