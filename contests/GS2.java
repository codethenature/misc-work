package hiring.contest;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class GS2 {
    static class Pair implements Comparable<Pair>{
        Integer idx, val;
        Pair(int index, int value) {
            idx=index;
            val=value;
        }
		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return this.val.compareTo(o.val);
		}
		public String toString() {
			return idx+ " " + val;
		}
    }
    static long buyMaximumProducts(int n, long k, int[] a) {
       ArrayList<Pair> pairs=new ArrayList<Pair>();
       long sum=0;
       for(int i=1; i<=n; i++) {
    	   pairs.add(new Pair(i, a[i-1]));
       }
       Collections.sort(pairs);
       //System.out.println(pairs);
       for(int i=0; i<pairs.size(); i++) {
    	   long val=pairs.get(i).val.intValue(), idx=pairs.get(i).idx.intValue();
    	   long canBuy=k/val;
    	   if(canBuy>idx) {
    		   k-=idx*val;
    		   sum+=idx;
    	   } else {
    		   k-=canBuy*val;
    		   sum+=canBuy;
    		   break;
    	   }
       }
       return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int arr_i = 0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        long k = in.nextLong();
        long result = buyMaximumProducts(n, k, arr);
        System.out.println(result);
        in.close();
    }
}

