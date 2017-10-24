import java.io.*;
import java.util.*;
public class angry {

	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("angry.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 
		 int N = Integer.parseInt(st.nextToken());
		 long[] locs = new long[N];
		 for(int i=0; i<N;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 locs[i]=2*Integer.parseInt(p.nextToken());
			 
		 }
		 Arrays.sort(locs);
		 long[] leftPower = new long[N];
		 for(int i=1; i<N;i++){
			 leftPower[i]=Integer.MAX_VALUE-10;
		 }
		 leftPower[0]=-2;
		 int queuefront = 0;
		 for(int i=1; i<N;i++){
			 while(queuefront+1<i && Math.abs(locs[i]-locs[queuefront+1])>leftPower[queuefront+1]+2){
				 queuefront++;
			 }
			 leftPower[i]=Math.min(Math.abs(locs[i]-locs[queuefront]), leftPower[queuefront+1]+2);
			 
		 }
		 long[] blocs = new long[N];
		 for(int i=0; i<blocs.length;i++){
			 blocs[i]=locs[N-1-i];
		 }
		 long[] rightPower = new long[N];
		 for(int i=1; i<N;i++){
			 rightPower[i]=Integer.MAX_VALUE-10;
		 }
		 rightPower[0]=-2;
		 int queuefront2 = 0;
		 for(int i=1; i<N;i++){
			 while(queuefront2+1<i && Math.abs(blocs[i]-blocs[queuefront2+1])>rightPower[queuefront2+1]+2){
				 queuefront2++;
			 }
			rightPower[i]=Math.min(Math.abs(blocs[i]-blocs[queuefront2]), rightPower[queuefront2+1]+2);
			 
		 }
		 
		 long[] rightrightPower = new long[N];
		 for(int i=0; i<blocs.length;i++){
			 rightrightPower[i]=rightPower[N-1-i];
		 }

		 
		 double result = Integer.MAX_VALUE-10;
		 int i=0;
		 int j = N-1;
		 while(i<j){
			 double bigP = Math.max(leftPower[i], rightrightPower[j]);
			 double pos = Math.max(((double)locs[j]-(double)locs[i])/2, 2+bigP);
			 result = Math.min(result, pos);
			 if(leftPower[i+1]<rightrightPower[j-1]){
				 i++;
			 }
			 else{
				 j--;
			 }
			 
		 }
		 
		 
		 
		 out.printf("%.1f\n", result/ 2.0);
		 out.close();
		 
	}
}
