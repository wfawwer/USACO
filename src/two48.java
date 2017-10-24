
import java.util.*;
import java.io.*;
public class two48 {

	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("248.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 
		 int N = Integer.parseInt(st.nextToken());
		 int[] nums = new int[N];
		 for(int i=0; i<N;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 nums[i]=Integer.parseInt(p.nextToken());
			 
		 }
		 
		 int[][] dp = new int[N][N];
		 int ans = 0;
		 for(int len = 1; len<=N; len++){
			 for(int i=0; i+len<=N;i++){
				 int j = i+len-1;
				 dp[i][j]=-1;
				 if(i==j){
					 dp[i][j]=nums[i];
				 }
				 for(int k=i; k<j;k++){
					 if(dp[i][k]==dp[k+1][j] && dp[i][k]>0){
						 dp[i][j]=Math.max(dp[i][j], dp[i][k]+1);
					 }
					 
				 }
				 ans = Math.max(ans, dp[i][j]);
			 }
			 
			 
		 }
		 out.println(ans);
		 out.close();
	}
}
