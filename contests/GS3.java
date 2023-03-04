package hiring.contest;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class GS3 {

	static int traderProfit(int kk, int n, int[] A) {
		int prev=0;
		int[][] dp=new int[kk+1][n+1];
		
		for(int i=1; i<=kk; i++) {
			int max=0;
			for(int j=2; j<=n; j++) {
				if(j==2)
					max=dp[i-1][1]-A[0];
				else if(max<dp[i-1][j-1]-A[j-2])
					max=dp[i-1][j-1]-A[j-2];
				
				dp[i][j]=Math.max(max+A[j-1], dp[i][j-1]);
			}
		}
		int max=0;
		for(int i=1; i<=kk; i++) {
			for(int j=2; j<=n; j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println("\n");
		}
		for(int i=1; i<=kk; i++) {
			max=Math.max(dp[i][n], max);
		}
		return max;
    }
	
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int k = in.nextInt();
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            int result = traderProfit(k, n, arr);
            System.out.println(result);
        }
        in.close();
    }

}
