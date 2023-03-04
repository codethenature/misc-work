package hiring.contest;

import java.math.BigInteger;

public class PeopleStrong1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]=new int[]{1, 2,4, 3, 1000000000};
		long aa=Integer.MAX_VALUE;
		System.out.println(solve(a));
	}
	static long solve(int[] a){
        BigInteger ans=BigInteger.ZERO;
        final long MOD=10000000011L;
        for(int i=0; i<a.length; i++) {
            long temp=0;
            while(a[i]!=0) {
                if((a[i]&1)==1) temp++;
                a[i]=a[i]>>1;
            }
            BigInteger base=BigInteger.valueOf(temp);
            ans=ans.add(base.modPow(BigInteger.valueOf(i+1), BigInteger.valueOf(MOD))) ;
        }
        return ans.mod(BigInteger.valueOf(MOD)).longValue();
    }
}
