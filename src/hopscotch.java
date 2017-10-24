import java.io.*;
import java.util.*;
public class hopscotch {


	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("hopscotch.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 int R = Integer.parseInt(st.nextToken());
		 int C = Integer.parseInt(st.nextToken());
		 int K = Integer.parseInt(st.nextToken());
		 int[][] vals = new int[R][C];
		 int[][] paths = new int[R][C];
		 paths[0][0]=1;
		 for(int i=0; i<R;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 for(int j=0; j<C;j++){
				 vals[i][j]=Integer.parseInt(p.nextToken());
			 }
		 }
		 
		 for(int i=0; i<R;i++){
			 for(int j=0; j<C;j++){
				 for(int k = 0; k<i;k++){
					 for(int l=0; l<j;l++){
						if(vals[i][j]!=vals[k][l]){ 
							paths[i][j]+=paths[k][l];
							paths[i][j]=paths[i][j]%1000000007;
						}
					 }
				 }
				
			 }
			 
		 }
		 
		
		 out.println(paths[R-1][C-1]%1000000007);
		 out.close();
		 
		 
	}
}
