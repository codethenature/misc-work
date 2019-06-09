package hiring.contests.cast;

import java.util.Scanner;

public class P1 {

	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		
		int test = sc.nextInt();
		
		while(test--!=0) {
			long x1 = sc.nextLong(), y1 = sc.nextLong();
			long x2 = sc.nextLong(), y2 = sc.nextLong();
			long x3 = sc.nextLong(), y3 = sc.nextLong();
			long x4 = sc.nextLong(), y4 = sc.nextLong();
			
			if((x1==x2 && y1==y2) || (x3==x4 && y3==y4)) {
				System.out.println("INVALID");
				continue;
			}
			
			if ( (x2-x1 == 0 && y4-y3 == 0) || (y2-y1 == 0 && x4-x3 == 0) ) { // perpendicular already
				System.out.println("YES");
				continue;
			}
			
			if ( (x2-x1 == 0 && y4-y3 != 0) || (y2-y1 != 0 && x4-x3 == 0) ) {
				System.out.println("NO");
				continue;
			}
			
			double m1 = (y2-y1)/(x2-x1);
			double m2 = (y4-y3)/(x4-x3);
			
			if(m1*m2 == -1) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}

	}

}
