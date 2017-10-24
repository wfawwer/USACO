import java.io.*;
import java.util.*;
public class circlecross {
	
	
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
		 for(int i=0; i<2*N;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 int index = Integer.parseInt(p.nextToken());
			 if(segments[index-1].aIndex<0){
				 segments[index-1].aIndex = i;
				 segments[index-1].axLoc=Math.cos(Math.PI*i/N);
				 segments[index-1].ayLoc=Math.sin(Math.PI*i/N);
			 }
			 else{
				 segments[index-1].bIndex = i;
				 segments[index-1].bxLoc=Math.cos(Math.PI*i/N);
				 segments[index-1].byLoc=Math.sin(Math.PI*i/N);
			 }
			 
		 }
		
		 
		 int counter = 0;
		 for(int i=0; i<N;i++){
			 double Ax = segments[i].axLoc;
			 double Ay=segments[i].ayLoc;
			 double Bx = segments[i].bxLoc;
			 double By=segments[i].byLoc;
			 for(int j=i; j<N;j++){
				 double Cx = segments[j].axLoc;
				 double Cy=segments[j].ayLoc;
				 double Dx = segments[j].bxLoc;
				 double Dy=segments[j].byLoc;	 

				 if( ccw(Ax,Ay,Cx,Cy,Dx,Dy) != ccw(Bx,By,Cx,Cy,Dx,Dy) && ccw(Ax,Ay,Bx,By,Cx,Cy) != ccw(Ax,Ay,Bx,By,Dx,Dy)){
	
					 counter++;
					 
				 }
			 }
		 }
		 out.println(counter);
		 out.close();
	}
	
	public static boolean ccw(double Ax, double Ay,double Bx,double By,double Cx,double Cy){
	
		 return (Cy-Ay) * (Bx-Ax) > (By-Ay) * (Cx-Ax);
		 
	}
}
