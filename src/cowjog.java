import java.io.*;
import java.util.*;

public class cowjog {
	public static class State implements Comparable<State>{
		long start;
		long end;
		public State(long start, long end){
			this.start = start;
			this.end = end;
		}
		
		public int compareTo(State s) {
			// TODO Auto-generated method stub
			return (int) (end-s.end);
		}
	}
	
	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("cowjog.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 int N = Integer.parseInt(st.nextToken());
		 long T = Long.parseLong(st.nextToken());
		 long[] start = new long[N];
		 long[] end = new long[N];
		 for(int i=0; i<N;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 start[i] = Long.parseLong(p.nextToken());
			 end[i]=start[i]+T*Long.parseLong(p.nextToken());
		 }
		 long num = 1;
		 long mark = end[N-1];
		 for(int i=N-1; i>=0;i--){
			 if(end[i]<mark){
				 mark = end[i];
				 num++;
			 }
		 }
		 
		 out.println(num);
		 out.close();
	}

}
