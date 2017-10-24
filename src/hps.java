import java.io.*;
import java.util.*;
public class hps {
	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("hps.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 int N = Integer.parseInt(st.nextToken());
		 int K = Integer.parseInt(st.nextToken());
		 int[] johnplay = new int[N];
		 for(int i=0; i<N;i++){
			 StringTokenizer pp = new StringTokenizer(f.readLine());
			 String ss = pp.nextToken();
			 if(ss.equals("H")){
				 johnplay[i]=0;
			 }
			 if(ss.equals("P")){
				 johnplay[i]=1;		
			 }
			 if(ss.equals("S")){
				 johnplay[i]=2;
			 }
		 }
		 int[][][] dp = new int[N+1][K+1][3];
		 for(int i=0; i<=N;i++){
			 for(int j=0; j<=K;j++){
				 for(int state = 0; state<3; state++){
					 if(i==0){
						 dp[i][j][state]=0;
					 }
					 else{
						 if(j==0){
							 dp[i][j][state] = dp[i-1][j][state] + (johnplay[i-1] == state ? 1 : 0);
						 }
						 else{
							 int up1 = (state+1)%3;
							 int up2 = (state+2)%3;
							 int temp=Math.max(dp[i-1][j-1][up1], dp[i-1][j-1][up2]);
							 dp[i][j][state]=Math.max(dp[i-1][j][state], temp) + (johnplay[i-1] == state ? 1 : 0);
							 
						 }
					 }
					 
					 
					 
				 }
			 }
		 }
		 
		
		 
		 int checker = Math.max(dp[N][K][1], dp[N][K][2]);
		 int sol = Math.max(dp[N][K][0], checker);
		 out.println(sol);
		 
		 out.close();
		 
	}
}
