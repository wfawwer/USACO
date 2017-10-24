import java.io.*;
import java.util.*;
public class altcirclecross{

public static class segment{
	int aIndex;
	int bIndex;
	double axLoc;
	double ayLoc;
	double bxLoc;
	double byLoc;
	public segment(int aIndex, int bIndex){
		this.aIndex = aIndex;
		this.bIndex = bIndex;
	}
	
	}

	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("circlecross.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 int N = Integer.parseInt(st.nextToken());
		 segment[] segments = new segment[N];
		 for(int i=0; i<N;i++){
			 segments[i]=new segment(-1,-1);
		 }
		 int[] vals = new int[2*N];
		 for(int i=0; i<2*N;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 int index = Integer.parseInt(p.nextToken());
			 
			 if(segments[index-1].aIndex<0){
				 segments[index-1].aIndex = i;
				 vals[i]=index;
			 }
			 else{
				 segments[index-1].bIndex = i;
				 vals[i]=-index;
			 }
			 
		 }
		 TreeSet<Integer> leftGuys = new TreeSet<Integer>();
		 TreeSet<Integer> rightGuys = new TreeSet<Integer>();
		 int[] processed = new int[N];
		
		 boolean seen = false;
		 int start = vals[0];
		 int end = 0;
		 for(int i=1; i<2*N;i++){
			 if(seen==false){
				 if(vals[i]!=-start){
				 rightGuys.add(vals[i]);
				 }
				 else{
					 seen = true;
					 end = i;
				 }
				 
			 }
			 else{
			 leftGuys.add(vals[i]);
			 }
		 }
		 
		 int counter = 0;
		 processed[start]=1;
		 for(int i=0; i<N-1;i++){
			 Iterator<Integer> itera =rightGuys.iterator();
			 while(itera.hasNext()){
				 int right =itera.next();
				 if(leftGuys.contains(-right)){
					 counter++;
				 }
			 }
			 
			 //updated counter for this//
			 while(processed[Math.abs(vals[start])%N]==1){
				 start++;
				 start = start%(2*N);
				 System.out.println(start);
			 }
			 while(vals[end]!=-vals[start]){
				 end++;
				 rightGuys.add(vals[end]);
				 leftGuys.remove(vals[end]);
				 
				 
			 }
			 System.out.println(start+" "+end);
			 
			 
			 
		 }
		 System.out.println(counter);
		 
		 
	}

	public static boolean ccw(double Ax, double Ay,double Bx,double By,double Cx,double Cy){
	
		 return (Cy-Ay) * (Bx-Ax) > (By-Ay) * (Cx-Ax);
		 
	}
}
