import java.io.*;
import java.util.*;

public class visitfj {
	
	public static class state{
		public int xLoc;
		public int yLoc;
		public int move;
		public state(int xLoc, int yLoc,int move){
			this.xLoc = xLoc;
			this.yLoc = yLoc;
			this.move = move;
		}
	}
	
	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("visitfj.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 
		 int N = Integer.parseInt(st.nextToken());
		 int T = Integer.parseInt(st.nextToken());
		 
		 int[][] grass = new int[N][N];
		 long[][][] dist = new long[N][N][3];
		 ArrayList<state> unvisited = new ArrayList<state>();
		 for(int i=0; i<N;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 for(int j=0; j<N;j++){
				 grass[i][j]=Integer.parseInt(p.nextToken());
				 dist[i][j][0]=Long.MAX_VALUE;
				 dist[i][j][1]=Long.MAX_VALUE;
				 dist[i][j][2]=Long.MAX_VALUE;
				 unvisited.add(new state(i,j,0));
				 unvisited.add(new state(i,j,1));
				 unvisited.add(new state(i,j,2));
			 }
		 }
		 dist[0][0][0]=0;
		 while(unvisited.size()!=0){
			int pointer = 0;
			long testcost = Long.MAX_VALUE;
			for(int i=0; i<unvisited.size();i++){
				if(dist[unvisited.get(i).xLoc][unvisited.get(i).yLoc][unvisited.get(i).move]<=testcost){
	
					testcost=dist[unvisited.get(i).xLoc][unvisited.get(i).yLoc][unvisited.get(i).move];
					pointer = i;
					
				}
				
			}
			
			//we have the min cost guy//
			//update distance of neighbors//
			int newmove = (unvisited.get(pointer).move+1)%3;
			int currentX=unvisited.get(pointer).xLoc;
			int currentY = unvisited.get(pointer).yLoc;
			long alt = 0;
			
			//up//
			if(currentX-1>=0){
				if(newmove==0){
					alt = dist[currentX][currentY][unvisited.get(pointer).move]+T+grass[currentX-1][currentY];
				}
				else{
					alt = dist[currentX][currentY][unvisited.get(pointer).move]+T; 
				}
				if(alt<dist[currentX-1][currentY][newmove]){
					dist[currentX-1][currentY][newmove]=alt;
				}
			}
			//down//
			if(currentX+1<N){
				if(newmove==0){
					alt = dist[currentX][currentY][unvisited.get(pointer).move]+T+grass[currentX+1][currentY];
				}
				else{
					alt = dist[currentX][currentY][unvisited.get(pointer).move]+T; 
				}
				if(alt<dist[currentX+1][currentY][newmove]){
					dist[currentX+1][currentY][newmove]=alt;
				}
			}
			//left//
			if(currentY-1>=0){
				if(newmove==0){
					alt = dist[currentX][currentY][unvisited.get(pointer).move]+T+grass[currentX][currentY-1];
				}
				else{
					alt = dist[currentX][currentY][unvisited.get(pointer).move]+T; 
				}
				if(alt<dist[currentX][currentY-1][newmove]){
					dist[currentX][currentY-1][newmove]=alt;
				}
			}
			//right//
			if(currentY+1<N){
				if(newmove==0){
					alt = dist[currentX][currentY][unvisited.get(pointer).move]+T+grass[currentX][currentY+1];
				}
				else{
					alt = dist[currentX][currentY][unvisited.get(pointer).move]+T; 
				}
				if(alt<dist[currentX][currentY+1][newmove]){
					dist[currentX][currentY+1][newmove]=alt;
				}
			}
			
			unvisited.remove(pointer);
			 
		 }
		 long checker = Math.min(dist[N-1][N-1][0], dist[N-1][N-1][1]);
		 long ans = Math.min(checker, dist[N-1][N-1][2]);
		 out.println(ans);
		 out.close();
		 
		 
	}

}
