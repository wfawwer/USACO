import java.io.*;
import java.util.*;

public class split {
	public static class cow implements Comparable<cow>{
		int xloc;
		int yloc;
		public cow(int xloc, int yloc){
			this.xloc = xloc;
			this.yloc = yloc;
		}
		
		public int compareTo(cow c) {
			return xloc-c.xloc;
		}
		
		public cow transpose(){
			return new cow(yloc, xloc);
		}
		
	}
	
	
	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("split.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 int N = Integer.parseInt(st.nextToken());
		 TreeMap<cow,Integer> heights = new TreeMap<cow,Integer>(new Comparator<cow>(){
		    	public int compare(cow c1, cow c2){
		    		if(c1.yloc!=c2.yloc){
		    			return c1.yloc-c2.yloc;
		    		}
		    		else{
		    			return c1.xloc-c2.xloc;
		    		}
		    	}
		    });
		 TreeMap<cow,Integer> transheights = new TreeMap<cow,Integer>(new Comparator<cow>(){
			 public int compare(cow c1, cow c2){
		    		if(c1.yloc!=c2.yloc){
		    			return c1.yloc-c2.yloc;
		    		}
		    		else{
		    			return c1.xloc-c2.xloc;
		    		}
		    	}
		    });
		 cow[] cows = new cow[N];
		 cow[] transcows = new cow[N];
		 for(int i=0; i<N;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 cows[i]=new cow(Integer.parseInt(p.nextToken()),Integer.parseInt(p.nextToken()));
			 heights.put(cows[i],i);
			 transcows[i]=cows[i].transpose();
			 transheights.put(transcows[i],i);
		 }
		 Arrays.sort(cows);
		 Arrays.sort(transcows);
		 
		 long area = (long)(cows[N-1].xloc-cows[0].xloc)*(long)(heights.lastKey().yloc-heights.firstKey().yloc);
		 
		 long twoarea = Long.MAX_VALUE;
		 TreeMap<cow,Integer> leftheights = new TreeMap<cow,Integer>(new Comparator<cow>(){
			 public int compare(cow c1, cow c2){
		    		if(c1.yloc!=c2.yloc){
		    			return c1.yloc-c2.yloc;
		    		}
		    		else{
		    			return c1.xloc-c2.xloc;
		    		}
		    	}
		    });
		 for(int i=0; cows[i].xloc<cows[N-1].xloc;){		 
			 int j = i+1;
			 while(j<cows.length && cows[i].xloc==cows[j].xloc){
				 j++;
			 }
			 for(int k=i; k<j;k++){
				 leftheights.put(cows[k], k);
				 heights.remove(cows[k]);
			 }
			 long altarea = (long)(cows[i].xloc-cows[0].xloc)*(long)(leftheights.lastKey().yloc-leftheights.firstKey().yloc)+
					 (long)(cows[N-1].xloc-cows[j].xloc)*(long)(heights.lastKey().yloc-heights.firstKey().yloc);
			 twoarea=Math.min(twoarea, altarea);
			 i=j;

		 }
	
		 TreeMap<cow,Integer> transleftheights = new TreeMap<cow,Integer>(new Comparator<cow>(){
			 public int compare(cow c1, cow c2){
		    		if(c1.yloc!=c2.yloc){
		    			return c1.yloc-c2.yloc;
		    		}
		    		else{
		    			return c1.xloc-c2.xloc;
		    		}
		    	}
		    });
		 for(int i=0; transcows[i].xloc<transcows[N-1].xloc;){
			 int j = i+1;
			 while(j<transcows.length && transcows[i].xloc==transcows[j].xloc){
				 j++;
			 }
			 for(int k=i; k<j;k++){
				 transleftheights.put(transcows[k], k);
				 transheights.remove(transcows[k]);

			 }
			 long altarea = (long)(transcows[i].xloc-transcows[0].xloc)*(long)(transleftheights.lastKey().yloc-transleftheights.firstKey().yloc)+
					 (long)(transcows[N-1].xloc-transcows[j].xloc)*(long)(transheights.lastKey().yloc-transheights.firstKey().yloc);
			 twoarea=Math.min(twoarea, altarea);
			 i=j;
		 }
		 out.println(area-twoarea);
		 out.close();
	}
	
	
	
	
}
