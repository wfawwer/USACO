import java.io.*;
import java.util.*;

public class fairphoto {
	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("fairphoto.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fairphoto.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 
		 int N = Integer.parseInt(st.nextToken());
		 int[] locs = new int[N];
		 int[] prefsum = new int[N];

		 StringTokenizer mop = new StringTokenizer(f.readLine());
		 locs[0]=Integer.parseInt(mop.nextToken());
		 String sop = mop.nextToken();
		 if(sop.equals("W")){
			 prefsum[0]=1;
		 }
		 else{
			 prefsum[0]=-1;
		 }
		 for(int i=1; i<N;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 locs[i]=Integer.parseInt(p.nextToken());
			 String s = p.nextToken();
			 if(s.equals("W")){
				 prefsum[i]=prefsum[i-1]+1;
			 }
			 else{
				 prefsum[i]=prefsum[i-1]-1;
			 }
		 }
		 
		 ArrayList<Integer> monotone = new ArrayList<Integer>();
		 ArrayList<Integer> monotonepointers = new ArrayList<Integer>();
		 int max = 0;
		 for(int i=0; i<N;i++){
			 int bound = prefsum[i];
			 if(bound>=0){
				 max=Math.max(max, locs[i+1]-1);
				 continue;
			 }
			 //we now are looking for the furthest back guy guy greater than K.//
			 
			 int bot = 0;
			 int top = monotone.size()-1;
			 while(bot<top){
				 int pointer = (bot+top)/2;
				 if(monotone.get(pointer)-bound>=0){
					 
				 }
				 if(pointer<bound){
					 
				 }
				 
				 
			 }
			 
			 
		 }
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	}
}