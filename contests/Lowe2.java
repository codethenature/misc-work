package hiring.contest;

import java.util.Scanner;

public class Lowe2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		int q=s.nextInt();
		
		while(q--!=0) {
			long n=s.nextLong();
			long k=s.nextInt();
			long digits=1, ans=0;
			
			while(true) {
				if(digits==1) {
					if(n>=k-1)
						ans+=k;
				} else {
					long temp=(long) Math.pow(k, digits)-1;
					System.out.println(temp);
					if(n>=temp) {
						ans+= digits*((long ) Math.pow(k, digits-1)*(k-1));
					}
					else {
						System.out.println("break"+temp);
						long prev=(long) Math.pow(k, digits-1)-1;
						ans+=(digits*(n-prev));
						break;
					}
				}
				digits++;
			}
			System.out.println("ans=="+ans);
		}
		
	}


}
