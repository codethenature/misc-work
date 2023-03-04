package hiring.contest;

import java.util.*;

public class Bima1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		int q=s.nextInt();		
		Map<Integer, Integer> prio=new HashMap<>();
		SortedSet<Request> set1=new TreeSet<>(new Comparator<Request>() {

			@Override
			public int compare(Request a, Request b) {
				return a.time.compareTo(b.time);
			}
			
		});
		SortedSet<Request> set2=new TreeSet<>(new Comparator<Request>() {

			@Override
			public int compare(Request a, Request b) {
				return a.priority.compareTo(b.priority);
			}
			
		});
		
		while(q--!=0) {
			int type=s.nextInt();
			
			
			switch(type) {
				case 1: {
					int time=s.nextInt(), priority=s.nextInt();
					if(prio.containsKey(time)) {
						set1.remove(new Request(time, prio.get(time)));
						set2.remove(new Request(time, prio.get(time)));
					}
					set1.add(new Request(time, priority));
					set2.add(new Request(time, priority));
					prio.put(time, priority);
					break;
				}
				case 2: {
					int time=s.nextInt();
					if(prio.containsKey(time)) {
						set1.remove(new Request(time, prio.get(time)));
						set2.remove(new Request(time, prio.get(time)));
						prio.remove(time);
					}
					
					break;
				}
				case 3: {
					System.out.println(set2.first().priority + " " + set2.last().priority);
					break;
				}
				case 4: {
					System.out.println(set1.last().priority);
					break;
				}	
			}
		}
	}

}
class Request {
	Integer time, priority;
	Request(int t, int p) {
		time=t;
		priority=p;
	}
}
