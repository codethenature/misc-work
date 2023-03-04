package hiring.contest;

import java.math.BigInteger;
import java.util.Scanner;

public class PayU2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
		int[] a=new int[n];
		
		for(int i=0; i<n; i++)
			a[i]=s.nextInt();
		System.out.println(findMaxSubset(a, n));
	}
	
	static int findMaxSubset(int a[], int n)
	{
	    
	    int max = a[0];
	    for (int i=1; i<n; i++)
	       if (a[i] > max)
	           max = a[i];
	 
	    
	    int m = (1 << (int)((Math.log(max)/Math.log(2)) + 1) ) - 1;
	 

	    int[][] dp=new int[n+1][m+1];
	 
	    
	    for (int i=0; i<=n; i++)
	        for (int j=0; j<=m; j++)
	            dp[i][j] = -1;
	 
	    
	    dp[0][0] = 0;
	 
	    for (int i=1; i<=n; i++)
	        for (int j=0; j<=m; j++) {
	        	int largest=-1;
	        	for(int k=1; k<i; k++) {
	        		if(largest<dp[k][j^a[i-1]])
	        			largest=dp[k][j^a[i-1]];
	        	}
	        	//int temp=((dp[i-1][j^a[i-1]]>-1)?dp[i-1][j^a[i-1]]+1:0);
	        	//dp[i][j] = (dp[i-1][j]<temp)?temp:dp[i-1][j];
	        	dp[i][j]=largest+1;
	        }
	             
	    
	    int ans=0, MOD=1000_000_007;
	    for(int j=0; j<=max; j++) {
	    	int largest=0;
	    	for(int i=1; i<=n; i++) {
	    		if(largest<dp[i][j])
	    			largest=dp[i][j];
	    	}
	    	ans=(ans+ (largest * (BigInteger.valueOf(31L).modPow(BigInteger.valueOf(j), BigInteger.valueOf(MOD)).intValue()))%MOD)%MOD;
	    }
	    return ans%MOD;
	}

}
