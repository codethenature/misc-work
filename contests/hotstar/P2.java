package hiring.contests.hotstar;

import java.util.Scanner;

public class P2 {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
        int t=s.nextInt();
        
        while(t--!=0) {
        	char[] sb = s.next().toCharArray();
        	int n=s.nextInt();
        	
        	for(int pos=0; pos<sb.length; pos++) {
        		if(checkVowel(sb[pos]) && n>0) {
        			sb[pos] = (char) ((int)sb[pos] + 1);
        			n--;
        		}
        		if(n==0)
        			break;
        	}
        	System.out.println(new String(sb));
        	
        }
	}

	public static boolean checkVowel(char c) {
		return  c == 'a' ||
				c == 'e' ||
				c == 'i' ||
				c == 'o' ||
				c == 'u' ;
	}

}
