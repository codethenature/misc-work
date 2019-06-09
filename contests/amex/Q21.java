package hiring.contests.amex;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Q21 {

	public static ArrayList<Integer>[] al;
	public static int ans[], height[];
	public static boolean visited[];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		al = new ArrayList[n+1];
		visited = new boolean[n+1];
		ans = new int[n+1];
		height = new int[n+1];
		
		for(int i=1; i<= n-1; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			
			if(al[u] == null) {
				al[u] = new ArrayList<>();
			}
			if(al[v] == null) {
				al[v] = new ArrayList<>();
			}
			al[u].add(v);
			al[v].add(u);
			
		}
		visited[1] = true;
		height(1);
		
		for(int i=1; i<=n; i++) {
			visited[i] = false;
		}
		
		visited[1] = true;
		rec(1);
		
		for(int i=1; i<=n; i++) {
			System.out.print(ans[i]);
			if(i != n)
				System.out.print(" ");
		}
		System.out.println();

	}
	
	public static int rec(int u) {
		
		int firstMax = 0, secondMax = 0;
		int heightSum =0, max=0;
		
		if(al[u] != null) {
			for(int i=0; i<al[u].size(); i++) {
				int v = al[u].get(i);
				if(visited[v])
					continue;
				visited[v] = true;
				int cur = rec(v);
				if(cur > max)
					max = cur;
				
				if(height[v]+1 > firstMax) {
					secondMax = firstMax;
					firstMax = height[v]+1;
				} else if(height[v]+1 > secondMax) {
					secondMax = height[v]+1;
				}
			}
			heightSum = firstMax + secondMax;
			ans[u] = Math.max(heightSum, max);
			return ans[u];
		} 
		
		return 0;
	}
	
	public static int height(int u) {
		int max = 0;
		
		if(al[u] != null) {
			for(int i=0; i<al[u].size(); i++) {
				if(visited[al[u].get(i)])
					continue;
				
				visited[al[u].get(i)] = true;
				
				int cur = (al[u].get(i));
				
				if(cur > max)
					max = cur;
			}	
		
		}
		height[u] = max;
		return max + 1;
	}

}
