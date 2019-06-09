package hiring.contests.trustingsocial;

import java.util.Scanner;

public class P1 {

    
    public static void main(String args[] ) throws Exception {

        Scanner s = new Scanner(System.in);
        int test = s.nextInt();
        
        while(test--!=0) {
            long s_p = s.nextLong(), s_q = s.nextLong(), s_r = s.nextLong(), s_s = s.nextLong();
            long d_p = s.nextLong(), d_q = s.nextLong(), d_r = s.nextLong(), d_s = s.nextLong();
            long f_p = s.nextLong(), f_q = s.nextLong(), f_r = s.nextLong(), f_s = s.nextLong();
            long c_p = s.nextLong(), c_q = s.nextLong(), c_r = s.nextLong(), c_s = s.nextLong();
            
            long f_score =
            		(long) (((s_p - (d_p * f_p)) < (s_p / 2) ) ?  ( s_p / 2 ) : (s_p - (d_p * f_p)  )) +
            		(long) (((s_q - (d_q * f_q)) < (s_q / 2) ) ?  ( s_q / 2 ) : (s_q - (d_q * f_q)  )) +
            		(long) (((s_r - (d_r * f_r)) < (s_r / 2) ) ?  ( s_r / 2 ) : (s_r - (d_r * f_r)  )) +
            		(long) (((s_s - (d_s * f_s)) < (s_s / 2) ) ?  ( s_s / 2 ) : (s_s - (d_s * f_s)  ));
            
            long c_score =
            		(long) (((s_p - (d_p * c_p)) < (s_p / 2) ) ?  ( s_p / 2 ) : (s_p - (d_p * c_p) )) +
            		(long) (((s_q - (d_q * c_q)) < (s_q / 2) ) ?  ( s_q / 2 ) : (s_q - (d_q * c_q) )) +
            		(long) (((s_r - (d_r * c_r)) < (s_r / 2) ) ?  ( s_r / 2 ) : (s_r - (d_r * c_r) )) +
            		(long) (((s_s - (d_s * c_s)) < (s_s / 2) ) ?  ( s_s / 2 ) : (s_s - (d_s * c_s) ));
            
            long f_time = Math.max( Math.max(f_p, f_q), Math.max(f_r, f_s));
            long c_time = Math.max( Math.max(c_p, c_q), Math.max(c_r, c_s));
            
            if(f_score > c_score) {
            	System.out.println("Flash");
            } else if(f_score < c_score) {
            	System.out.println("Cisco");
            } else {
            	if(f_time > c_time) {
            		System.out.println("Cisco");
            	} else if(f_time < c_time) {
            		System.out.println("Flash");
            	} else {
            		System.out.println("Tie");
            	}
            }
        }
        

    }


}
