import java.io.*;
import java.util.*;
public class radio {

	public static void main(String[] args) throws IOException{
		
		 BufferedReader f = new BufferedReader(new FileReader("radio.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 
		 int N = Integer.parseInt(st.nextToken());
		 int M = Integer.parseInt(st.nextToken());
		 int[] johnx = new int[N+1];
		 int[] johny = new int[N+1];
		 int[] cowx = new int[M+1];
		 int[] cowy = new int[M+1];

		 StringTokenizer p = new StringTokenizer(f.readLine());
		 johnx[N]=Integer.parseInt(p.nextToken());
		 johny[N]=Integer.parseInt(p.nextToken());
		 StringTokenizer pp = new StringTokenizer(f.readLine());
		 cowx[M]=Integer.parseInt(pp.nextToken());
		 cowy[M]=Integer.parseInt(pp.nextToken());
		 
		 StringTokenizer ppp = new StringTokenizer(f.readLine());
		 String s =ppp.nextToken();
		 for(int i=0; i<s.length();i++){
			 char c = s.charAt(i);
			 if(c==('N')){
				 johny[N-i-1]=johny[N-i]+1;
				 johnx[N-i-1]=johnx[N-i];
			 }
			 if(c=='S'){
				 johny[N-i-1]=johny[N-i]-1;
				 johnx[N-i-1]=johnx[N-i];
			 }
			 if(c=='E'){
				 johnx[N-i-1]=johnx[N-i]+1;
				 johny[N-i-1]=johny[N-i];
			 }
			 if(c=='W'){
				 johnx[N-i-1]=johnx[N-i]-1;
				 johny[N-i-1]=johny[N-i];
			 }
			 
		 }
		 StringTokenizer pppp = new StringTokenizer(f.readLine());
		 String ss =pppp.nextToken();
		 for(int i=0; i<ss.length();i++){
			 char c = ss.charAt(i);
			 if(c=='N'){
				 cowy[M-i-1]=cowy[M-i]+1;
				 cowx[M-i-1]=cowx[M-i];
			 }
			 if(c=='S'){
				 cowy[M-i-1]=cowy[M-i]-1;
				 cowx[M-i-1]=cowx[M-i];
			 }
			 if(c=='E'){
				 cowx[M-i-1]=cowx[M-i]+1;
				 cowy[M-i-1]=cowy[M-i];
			 }
			 if(c=='W'){
				 cowx[M-i-1]=cowx[M-i]-1;
				 cowy[M-i-1]=cowy[M-i];
			 }
			 
		 }
	
		 long[][] dp = new long[N+1][M+1];
		 for(int i=0; i<dp.length;i++){
			 for(int j=0; j<dp[i].length;j++){
				 if(i==0){
					 if(j==0){
						 dp[i][j]=0;
					 }
					 else{
						dp[i][j]= dp[i][j-1]+(cowx[j-1]-johnx[i])*(cowx[j-1]-johnx[i])+(cowy[j-1]-johny[i])*(cowy[j-1]-johny[i]);
					 }
				 }
				 else{
					 if(j==0){
						 dp[i][j]=dp[i-1][j]+(cowx[j]-johnx[i-1])*(cowx[j]-johnx[i-1])+(cowy[j]-johny[i-1])*(cowy[j]-johny[i-1]);
					 }
					 else{
						 long onc =dp[i][j-1]+(cowx[j-1]-johnx[i])*(cowx[j-1]-johnx[i])+(cowy[j-1]-johny[i])*(cowy[j-1]-johny[i]);
						 long twc= dp[i-1][j]+(cowx[j]-johnx[i-1])*(cowx[j]-johnx[i-1])+(cowy[j]-johny[i-1])*(cowy[j]-johny[i-1]);
						 long thrc = dp[i-1][j-1]+(cowx[j-1]-johnx[i-1])*(cowx[j-1]-johnx[i-1])+(cowy[j-1]-johny[i-1])*(cowy[j-1]-johny[i-1]);
						 dp[i][j]=Math.min(onc,twc);
						 dp[i][j]=Math.min(dp[i][j], thrc);
					 }
					 
					 
				 }
		 
			 }
		 }
		 
		out.println(dp[N][M]);
		 out.close();
 
		 
	}

}
