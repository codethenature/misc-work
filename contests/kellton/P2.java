package hiring.contests.kellton;

import java.util.Arrays;
import java.util.Scanner;

public class P2 {

	public static void main(String[] args) {


        Scanner s = new Scanner(System.in);
        int test = s.nextInt();
        
        while(test--!=0) {
            int m= s.nextInt();
            int n= s.nextInt();
            int[] ccount= new int[26];
            Arrays.fill(ccount, 0);
            
            for(int i=0; i<m; i++) {
                char[] temp=s.nextLine().toCharArray();
                for(char c : temp) {
                    ccount[c-'a']++;
                }
            }
            boolean success = true;
            
            if(m%2==0 && n%2==0) {
                for(int count : ccount) {
                	if(count%4!=0) {
                		success = false;
                		break;
                	}
                }
            } else if(m%2==1 && n%2==1) {
                for(int i=0; i<26; i++) {
                	ccount[i]%= 4;
                }
                int requiredPairsfor2= m+n-2;
                int temp=0;
                for(int i=0; i<26; i++) {
                	if (temp + ccount[i]/2 < requiredPairsfor2) {
                		temp += ( ccount[i]/2 )*2;
                		ccount[i]%=2;
                	} else if(temp + ccount[i]/2 == requiredPairsfor2) {
                		temp += ( ccount[i]/2 )*2;
                		ccount[i]%=2;
                		break;
                	} else {
                		ccount[i] -= requiredPairsfor2 - temp;
                		temp = requiredPairsfor2;
                	}
                }
                int sum=0;
                for(int i=0; i<26; i++) {
                	 sum+=ccount[i];
                }
                if(sum!=1) {
                	success = false;
                }
            } else {
            	for(int i=0; i<26; i++) {
                	ccount[i]%= 4;
                }
                int requiredPairsfor2= (m%2!=0)?m-1:n-1;
                int temp=0;
                for(int i=0; i<26; i++) {
                	if (temp + ( ccount[i]/2 )*2 < requiredPairsfor2) {
                		temp += ( ccount[i]/2 )*2;
                		ccount[i]%=2;
                	} else if(temp + ( ccount[i]/2 )*2 == requiredPairsfor2) {
                		temp += ( ccount[i]/2 )*2;
                		ccount[i]%=2;
                		break;
                	} else {
                		ccount[i] -= requiredPairsfor2 - temp;
                		temp = requiredPairsfor2;
                		break;
                	}
                }
                int sum=0;
                for(int i=0; i<26; i++) {
                	 sum+=ccount[i];
                }
                if(sum!=0) {
                	success = false;
                }
            }
            if(success)
            	System.out.println("YES");
            else
            	System.out.println("NO");
        }

	}

}
