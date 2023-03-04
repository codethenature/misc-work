package hiring.contest;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class GS5 {
	private static double[][] dp;
	private static int mod=1000_000_007;
	
	static double currenciesRec(int n, int x, int s, int f, int m, double[][] A) {
		if(m>1000000)
			return 0.0;
		dp=new double[m+1][n];
		for(int i=1; i<=m; i++) {
			for(int j=0; j<n; j++) {
				dp[i][j]=-1;
			}
		}
		return rec(n, x, s, f, m, A);
		
	}
	public static double rec(int n, double x, int s, int f, int m, double[][] A) {
    	if(s==f && m==0)
    		return x;
    	else if(m==0 && s!=f) 
    		return -1.0;
    	else if(dp[m][s]!=-1)
    		return dp[m][s];
    	
    	double max=-1.0;
		for(int j=0; j<n; j++) {
			if(j!=s) {
				max=Math.max(max, rec(n, (x*A[s][j])%mod, j, f, m-1, A));
			}
		}
		return dp[m][s]=max%mod;
    }
    static long currencies1(int n, int x, int s, int f, int m, double[][] A) {
    	if(m>1000000)
    			return 0;
    	BigInteger[][] dp=new BigInteger[m+1][n+1];
    	int mod=1000000007;
    	
    	for(int j=1; j<=n; j++) {
    		dp[1][j]=BigInteger.valueOf((long)A[s][j-1]).multiply(BigInteger.valueOf(x));
    	}
    	
    	for(int i=2; i<=m; i++) {
    		for(int j=1; j<=n; j++) {
    			
    			BigInteger max=dp[i-1][1].multiply(BigInteger.valueOf((long)A[0][j-1]));
    			for(int k=2; k<=n; k++) {
    				max= max.max(dp[i-1][k].multiply(BigInteger.valueOf((long)A[k-1][j-1])));
    			}
    			dp[i][j]=max;
    		}
    	}
    	BigInteger max=dp[m][1];
    	for(int i=2; i<=n; i++) {
    		max=max.max(dp[m][i]);
    	}
    	return max.remainder(BigInteger.valueOf(mod)).longValue();
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int x = in.nextInt();
        int s = in.nextInt();
        int f = in.nextInt();
        int m = in.nextInt();
        double[][] A = new double[n][n];
        for(int A_i = 0; A_i < n; A_i++){
            for(int A_j = 0; A_j < n; A_j++){
                A[A_i][A_j] = in.nextDouble();
            }
        }
        System.out.println((long)currencies1(n, x, s, f, m, A));
        in.close();
    }
}