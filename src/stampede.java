import java.io.*;
import java.util.*;

public class stampede {
	public static class cow{
		public int start;
		public int yLoc;
		public int speed;
		public int ID;
		public cow(int start, int yLoc, int speed, int ID){
			this.start = start;
			this.yLoc = yLoc;
			this.speed = speed;
			this.ID = ID;
		}
		
	}
	public static class event{
		public int ID;
		public int loc;
		public int in;
		public event(int ID, int loc, int in){
			this.ID = ID;
			this.loc = loc;
			this.in = in;
		}
	}
	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("stampede.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stampede.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 
		 int N = Integer.parseInt(st.nextToken());
		 cow[] cows = new cow[N];
		 int[] visible = new int[N];
		 TreeSet<cow> frontcows = new TreeSet<cow>(new Comparator<cow>(){
		    	public int compare(cow c1, cow c2){
		    		if(c1.yLoc!=c2.yLoc){
		    			return (int) (c1.yLoc-c2.yLoc);
		    		}
		    		else{
		    			return (int) (c1.yLoc-c2.yLoc);
		    		}
		    	}
		    });
		 event[] events = new event[2*N];
		 for(int i=0; i<N;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 cow c = new cow(Integer.parseInt(p.nextToken()),Integer.parseInt(p.nextToken()), Integer.parseInt(p.nextToken()),i);
			 cows[i]=c;
			 events[2*i]=new event(i, Math.abs(c.start*c.speed), 0);
			 events[2*i+1]=new event(i,Math.abs(c.start*c.speed)-c.speed,1);
		 }
		 
		 Arrays.sort(events, new Comparator<event>() {
			    public int compare(event e1, event e2) {
			        return e1.loc-e2.loc;
			    }
			});
		 /*
		 for(int i=0; i<events.length;i++){
			 System.out.println(events[i].ID+" "+events[i].loc+" "+events[i].in);
		 }
		 System.out.println();
		 */
		 for(int i=0; i<events.length;i++){
			 //fist process cows//
			 int j=i;
			 while(j<2*N && events[j].loc==events[i].loc){
				 if(events[j].in==1){
					 frontcows.add(cows[events[j].ID]);
				 }
				 else{
					 frontcows.remove(cows[events[j].ID]);
				 }
				 j++;
			 }
			 i=j-1;
			 
			 if(frontcows.size()!=0){
				 visible[frontcows.first().ID]=1;
				 //then add seen//
			 }
		 }
		 
		 int counter = 0;
		 for(int i=0; i<visible.length;i++){
			 if(visible[i]==1){
				 counter++;
			 }
		 }
		 out.println(counter);
		 out.close();
		 
		 //event = cow reaches 0//
		 //do binary tree stuff/
	}
	
}
		 