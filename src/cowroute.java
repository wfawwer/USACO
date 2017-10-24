import java.io.*;
import java.util.*;

public class cowroute {
	public static class cost{
		public long cost;
		public int flightsteps;
		
		public cost(int cost, int flightsteps){
			this.cost=cost;
			this.flightsteps = flightsteps;
		}
		
	}
	
	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("cowroute.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowroute.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 
		 int A = Integer.parseInt(st.nextToken());
		 int B = Integer.parseInt(st.nextToken());
		 int N = Integer.parseInt(st.nextToken());
		 cost[][] costs = new cost[1001][1001];
		 for(int i=0; i<costs.length;i++){
			 for(int j=0; j<costs[i].length;j++){
				 costs[i][j]= new cost(-1,0);
			 }
		 }
		 for(int i=0; i<N;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 long cost = Long.parseLong(p.nextToken());
			 int size = Integer.parseInt(p.nextToken());
			 int[] nodes = new int[size];
			 StringTokenizer pp = new StringTokenizer(f.readLine());
			 for(int j=0; j<size;j++){
				nodes[j]=Integer.parseInt(pp.nextToken());
			 }
			 
			 for(int j=0; j<size;j++){
				 for(int k=j+1; k<size;k++){
					 if(costs[nodes[j]][nodes[k]].cost==-1){
						 costs[nodes[j]][nodes[k]].cost=cost;
						 costs[nodes[j]][nodes[k]].flightsteps=k-j;
					 }
					 else{
						 if(cost<costs[nodes[j]][nodes[k]].cost){
							 costs[nodes[j]][nodes[k]].cost=cost;
							 costs[nodes[j]][nodes[k]].flightsteps=k-j;
						 }
					 }
				 }
			 }
	 
		 }
		long[] dist = new long[1001];
		long[] steps = new long[1001];
		int[] visited = new int[1001];
		int visitedCounter = 0;
		for(int i=1; i<dist.length;i++){
			if(i!=A){
				dist[i]=Long.MAX_VALUE;
				steps[i]=Long.MAX_VALUE;
			}
		}

		while(visitedCounter !=1000){
			long minCost = Long.MAX_VALUE;
			int pointer = -1;
			for(int i=1; i<dist.length;i++){
				if(dist[i]<minCost && visited[i]!=1){
						minCost = dist[i];
						pointer = i;
				}
			}
			if(pointer==-1){
				break;
			}
			visited[pointer]=1;
			visitedCounter++;
			
			for(int i=0; i<costs[pointer].length;i++){
				if(costs[pointer][i].cost!=-1){
					long alt=dist[pointer]+costs[pointer][i].cost;
					if(alt<dist[i]){
						dist[i]=alt;
						steps[i]=steps[pointer]+costs[pointer][i].flightsteps;
					}
					
				}
				
			}
			
		}
		
		
		
		
		if(dist[B]==Long.MAX_VALUE){
			System.out.println("-1 -1");
		}
		else{
			System.out.println(dist[B]+" "+steps[B]);
		}
		out.close();
		 
		 
		 
		 
		 
		 
		 
		 
	}
}
