package hiring.contest;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class GS4 {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] t = new int[n];
        for(int t_i = 0; t_i < n; t_i++){
            t[t_i] = in.nextInt();
        }
        int[] p = new int[n];
        for(int p_i = 0; p_i < n; p_i++){
            p[p_i] = in.nextInt();
        }
        ArrayList<History> list1=new ArrayList<>();
        ArrayList<History> list2=new ArrayList<>();
        for(int i=0; i<n; i++) {
        	list1.add(new History(t[i], p[i]));
        	list2.add(new History(t[i], p[i]));
        	
        }
        Collections.sort(list1, new Comparator<History>() {

			@Override
			public int compare(History o1, History o2) {
				if(o1.price<o2.price)
					return -1;
				else if(o1.price>o2.price)
					return 1;
				else {
					if(o1.time<o2.time)
						return -1;
					else
						return 1;
				}
			}
        	
		});
        int min=list1.get(list1.size()-1).time;
        for(int i=list1.size()-2; i>=0; i--) {
        	if(list1.get(i).time>min) {
        		list1.get(i).time=min;
        	} else 
        		min=list1.get(i).time;
        }
        Collections.sort(list2, new Comparator<History>() {

			@Override
			public int compare(History o1, History o2) {
				return Integer.valueOf(o1.time).compareTo(o2.time);
			}
        	
		});
        int max=list2.get(list2.size()-1).price;
        for(int i=list2.size()-2; i>=0; i--) {
        	if(list2.get(i).price<max) {
        		list2.get(i).price=max;
        	} else 
        		max=list2.get(i).price;
        }
        //System.out.println(list1);
        //System.out.println(list2);
        for(int a0 = 0; a0 < q; a0++){
            int _type = in.nextInt();
            int v = in.nextInt();
            if(_type==1) {
            	System.out.println(bsearch(list1, v));
            } else {
            	System.out.println(bsearch2(list2, v));
            }
        }
        in.close();
    }
	public static int bsearch(ArrayList<History> list, int price) {
		int i=0, j=list.size()-1;
		
		while(i<j) {
			int mid=(i+j)/2;
			if(list.get(mid).price>=price) {
				j=mid;
			} else
				i=mid+1;
		}
		return list.get(i).price>=price?list.get(i).time:-1;
	}
	public static int bsearch2(ArrayList<History> list, int time) {
		int i=0, j=list.size()-1;
		
		while(i<j) {
			int mid=(i+j)/2;
			if(list.get(mid).time>=time) {
				j=mid;
			} else
				i=mid+1;
		}
		return list.get(i).time>=time?list.get(i).price:-1;
	}
	static class History{
		int time, price;

		public History(int time, int price) {
			super();
			this.time = time;
			this.price = price;
		}

		@Override
		public String toString() {
			return "History [time=" + time + ", price=" + price + "]";
		}
		
	}
}
