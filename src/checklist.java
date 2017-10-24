
import java.util.*;
import java.io.*;
public class checklist {

	public static void main(String[] args) throws IOException {
		 BufferedReader f = new BufferedReader(new FileReader("checklist.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());

		int H = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		
		int[] holsteinx = new int[H];
		int[] holsteiny = new int[H];
		int[] guernseyx = new int[G];
		int[] guernseyy = new int[G];
		for(int i=0; i<H;i++){
			StringTokenizer p = new StringTokenizer(f.readLine());
			holsteinx[i]=Integer.parseInt(p.nextToken());
			holsteiny[i]=Integer.parseInt(p.nextToken());
			
		}
		for(int i=0; i<G;i++){
			StringTokenizer p = new StringTokenizer(f.readLine());
			guernseyx[i]=Integer.parseInt(p.nextToken());
			guernseyy[i]=Integer.parseInt(p.nextToken());
			
		}
		
		

		long[][][] dp = new long[H+1][G+1][2];
		for(int i=0; i<dp.length;i++){
			for(int j=0; j<dp[i].length;j++){
				dp[i][j][0]=1L<<60;
				dp[i][j][1]=1L<<60;
			}
		}
		dp[1][0][0]=0;   //this is the first smart thing they did which takes care of a beginning case//
		//first set up boundaries//

		for(int i=0; i<dp.length;i++){//also a smart thing- always easier to go from start forwards//
			for(int j=0; j<dp[i].length;j++){
				for(int k=0; k<2;k++){
					if(k==0 && i==0) continue; 
					if(k==1 && j==0) continue;
				
					int cowx = 0;
					int cowy = 0;
					if(k==0){
						cowx = holsteinx[i-1];
						cowy = holsteiny[i-1];
					}
					if(k==1){
						cowx = guernseyx[j-1];
						cowy = guernseyy[j-1];
					}
					if(i<H)    //even if i==H we might still be able to fix j//
						{
					dp[i+1][j][0]= Math.min(dp[i+1][j][0], 
							dp[i][j][k]+(holsteinx[i]-cowx)*(holsteinx[i]-cowx)+(holsteiny[i]-cowy)*(holsteiny[i]-cowy)
							);
					}
					if(j<G){
					dp[i][j+1][1]= Math.min(dp[i][j+1][1], 
							dp[i][j][k]+(guernseyx[j]-cowx)*(guernseyx[j]-cowx)+(guernseyy[j]-cowy)*(guernseyy[j]-cowy)
							);
					}
				}

			}

		}
		
		
		
		
		long good = dp[H][G][0];
		out.print(good);
		out.close();
		
		
		
	}

}
