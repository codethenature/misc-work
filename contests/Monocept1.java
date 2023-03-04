package hiring.contest;

import java.util.Scanner;

public class Monocept1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		
		int test=s.nextInt();
		
		while(test--!=0) {
			int n=s.nextInt(), totalOtherChar=s.nextInt();
			s.nextLine();
			String text=s.nextLine();
			int left=0, countOtherChar=0, maxlength=0;
			
			for(char i='a'; i<='z'; i++) {
				left=0; countOtherChar=0;
				for(int j=0; j<n; j++) {
					
					if(text.charAt(j)==i) {
						
					} else {
						if(countOtherChar<totalOtherChar) {
							countOtherChar++;
						} else {
							while(left<n && text.charAt(left)==i) left++;
							left++;
						}
					}
					
					if(maxlength< (j-left+1)) maxlength= j-left+1;
				}
				
			}
			
			
			for(char i='A'; i<='Z'; i++) {
				left=0; countOtherChar=0;
				for(int j=0; j<n; j++) {
					
					if(text.charAt(j)==i) {
						
					} else {
						if(countOtherChar<totalOtherChar) {
							countOtherChar++;
						} else {
							while(left<n && text.charAt(left)==i) left++;
							left++;
						}
					}
					
					if(maxlength< (j-left+1)) maxlength= j-left+1;
				}
			}
			
			System.out.println(maxlength);
		}
	}

}
