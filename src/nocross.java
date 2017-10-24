import java.io.*;
import java.util.*;

public class nocross {

	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("nocross.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 
		 int N = Integer.parseInt(st.nextToken());
		 int[] leftCows = new int[N];
		 int[] rightCows = new int[N];
		 for(int i=0; i<N;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 leftCows[i]=Integer.parseInt(p.nextToken());
		 }
		 for(int i=0; i<N;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 rightCows[i]=Integer.parseInt(p.nextToken());
		 }
		 
		 int[][] dp = new int[N][N];
		 for(int i=0; i<N;i++){
			 for(int j=0; j<N;j++){
				 if(i==0 || j==0){
					if(Math.abs(leftCows[i]-rightCows[j])<=4){
						dp[i][j]=1;
					}
					 
				 }
				 else{
					int weight = 0; 
					if(Math.abs(leftCows[i]-rightCows[j])<=4){
						weight=1;
					}
					int checker = Math.max(dp[i-1][j], dp[i][j-1]);
					dp[i][j]=Math.max(weight+dp[i-1][j-1], checker); 
					 
				 }

			 }
		 }
		 
		 out.println(dp[N-1][N-1]);
		 out.close();
	}

}
