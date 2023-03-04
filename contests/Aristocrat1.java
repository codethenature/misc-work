package hiring.contest;

import java.util.Scanner;

public class Aristocrat1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		int t=s.nextInt();
		
		while(t--!=0) {
			int n=s.nextInt(),a=s.nextInt(),b=s.nextInt(),c=s.nextInt();
			int ans=(n/a)+(n/b)+(n/c);
			ans-=(n/(a*b))+(n/(a*c))+(n/(b*c));
			ans-=n/(a*b*c);
			System.out.println(ans);
		}
	}

}
