package hiring.contest;

public class Lowe1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		pre();
		System.out.println(data[0]+" "+data[1]+" " +data[3]);
	}
    static int[] data= new int[1000001];

    static void pre() {
    	data[0]=0;
        for(int i=1; i<=1000000; i++) {
            
            if(checkDecPalin(i)) {
            	if(checkBinPalin(i))
            		data[i]=data[i-1]+1;
            	else
            		data[i]=data[i-1];
            } else {
            	data[i]=data[i-1];
            }
        }
    }
    static boolean checkDecPalin(int dec) {
    	String temp=String.valueOf(dec);
    	for(int i=0, j=temp.length()-1; i<j; i++, j--) {
    		if(temp.charAt(i)!=temp.charAt(j))
    			return false;
    	}
    	return true;
    }
    static boolean checkBinPalin(int dec) {
    	StringBuilder sb=new StringBuilder();
    	while(dec!=0) {
    		if((dec&1) ==1) sb.append('1');
    		else sb.append('0');
    		dec=dec>>1;
    	}
    	
    	String temp=sb.reverse().toString();
    	for(int i=0, j=temp.length()-1; i<j; i++, j--) {
    		if(temp.charAt(i)!=temp.charAt(j))
    			return false;
    	}
    	return true;
    }

}
