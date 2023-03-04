package hiring.contest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Groupon2 {
	
	private static ArrayList<Integer>[] al;
	private static int n;
	private static Scanner s;
	private static boolean[] vis, prime;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sievePrime();
		s=new Scanner(System.in);
		n=s.nextInt();
		al=new ArrayList[n+1];
		
		for(int i=0; i<n-1; i++) {
			int x=s.nextInt(), y=s.nextInt();
			if(al[x]==null)
				al[x]=new ArrayList<>();
			if(al[y]==null)
				al[y]=new ArrayList<>();
			al[x].add(y);
			al[y].add(x	);
		}
		
		int q=s.nextInt();
		
		while(q--!=0) {
			int u=s.nextInt(), v=s.nextInt();
			vis= new boolean[n+1];
			for(int i=0; i<n+1; i++) vis[i]=false;
			
			System.out.println(findPathPrimes(u, v, checkPrime(u)?1:0));
			
			
		}
		
	}
	
	public static int findPathPrimes(int u, int v, int count ) {
		if(u==v) {
			vis[u]=true;
			return count;
		}
		
		vis[u]=true;
		
		for(int i=0; i<al[u].size(); i++) {
			int y=al[u].get(i);
			if(vis[y]==false) {
				int tcount=findPathPrimes(y,v, checkPrime(y)?count+1:count);
				 if(tcount>-1) {
					 return tcount;
				 }
			}
		}
		return -1;
	}
	
	public static boolean checkPrime(int n) {
		if(n==1) return false;
		else return prime[n];
	}
	
	public static void sievePrime() {
		int n=2*100000+1;
		prime = new boolean[n+1];
        for(int i=0;i<n;i++)
            prime[i] = true;
         
        for(int p = 2; p*p <=n; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for(int i = p*2; i <= n; i += p)
                    prime[i] = false;
            }
        }
	}

}
