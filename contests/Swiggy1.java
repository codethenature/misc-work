package hiring.contest;

import java.util.Scanner;

public class Swiggy1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		String n=sc.nextLine();
		StringBuilder ans=new StringBuilder();
		
		int len=n.length();
		
		for(int i=0; i<len; i++) {
			int digit= n.charAt(i) - '0';
			
			switch(digit) {
				case 0 : case 1 : {
					ans.delete(0, ans.length());
					ans.append(make7(len-1));
				}
					break;
				case 4 : case 6 : case 8 : {
					ans.append(String.valueOf(digit-1));
					ans.append(make7(len-i-1));
				}
					break;
				case 9 : {
					ans.append(make7(len-i));
				}
					break;
				default : {
					ans.append(String.valueOf(digit));
				}	
				
			}
			if(digit<=1 || digit%4==0 || digit==9 || digit==6)
				break;
		}
		System.out.println(ans);
	}
	
	public static StringBuilder make7(int len) {
		 StringBuilder sb= new StringBuilder();
		 
		 for(int i=0; i<len; i++) {
			 sb.append('7');
		 }
		 return sb;
	}

}
