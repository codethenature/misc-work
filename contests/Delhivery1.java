package hiring.contest;

import java.util.Arrays;
import java.util.Scanner;

public class Delhivery1 {
	
	public static long memo[]= new long[1001];
	
	public static void main(String[] args) {

		Scanner s= new Scanner(System.in);
		int test=s.nextInt();
		int MOD=1000_000_009;
		
		while(test--!=0) {
			int A=s.nextInt(), S=s.nextInt();
		
			Arrays.fill(memo, 0L);
			memo[0]=1L;
			
			for(int i=S; i<=A; i++) {
				for(int j=S; j<=i/2; j++) {
					memo[i]=(memo[i] + (memo[j]*memo[i-j])%MOD)%MOD;
				}
				memo[i]= (memo[i]+1L)%MOD;
			}
			System.out.println(memo[A]);
		}
	}
}
