package hiring.contests.adobe;

import java.util.HashSet;
import java.util.Scanner;

public class P1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		StringBuffer sb= new StringBuffer(s.nextLine());
		HashSet<Character> hash = new HashSet<>();
		
		for(int i=0; i < sb.length(); i++) {
			if(hash.contains(Character.toUpperCase(sb.charAt(i))) || 
					hash.contains(Character.toLowerCase(sb.charAt(i)))) {
				hash.remove(Character.toLowerCase(sb.charAt(i)));
				hash.remove(Character.toUpperCase(sb.charAt(i)));
				sb.deleteCharAt(i);
			}
			else {
				hash.add(sb.charAt(i));
			}
		}
		System.out.println(sb);
	}

}
