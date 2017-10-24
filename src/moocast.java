
import java.util.*;
import java.io.*;
public class moocast {
	public static class edge implements Comparable<edge>{
		int from;
		int to;
		int len;
		
		public edge(int from, int to, int len){
			this.from =from;
			this.to= to;
			this.len = len;
		}
		public int compareTo(edge e) {
			return len-e.len;
		}
		
	}
	public static class pointer{
		pointer parent;
		int rank;
	
		public pointer(){
			this.parent = this;
			this.rank=0;
		}
		
		
	}
	
	public static pointer Find(pointer p){
		if(p.parent!=p){
			p.parent = Find(p.parent);
		}
		return p.parent;
	}
	
	public static void Union(pointer x, pointer y){
		pointer xRoot = Find(x);
		pointer yRoot = Find(y);
		if(xRoot==yRoot){
			return;
		}
		else{
			if(xRoot.rank<yRoot.rank){
				xRoot.parent = yRoot;
		
			}
			else{
				if(xRoot.rank>yRoot.rank){
					yRoot.parent = xRoot;
				}
				else{
					yRoot.parent = xRoot;
					xRoot.rank = xRoot.rank+1;
				}
				
				
			}
		
		}
		
	}

	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("moocast.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());


		int N = Integer.parseInt(st.nextToken());
		int[] cowx = new int[N];
		int[] cowy = new int[N];
		for(int i=0; i<N;i++){
			StringTokenizer p = new StringTokenizer(f.readLine());
			cowx[i]=Integer.parseInt(p.nextToken());
			cowy[i]=Integer.parseInt(p.nextToken());
				
		}
		ArrayList<edge> edges = new ArrayList<edge>();
		
		for(int i=0; i<N;i++){
			for(int j=i+1; j<N;j++){
				edges.add(new edge(i,j, (cowx[i]-cowx[j])*(cowx[i]-cowx[j]) + (cowy[i]-cowy[j])*(cowy[i]-cowy[j]) ));
				
			}
			
		}
		
		//sort edges with the custom comparator//
		Collections.sort(edges);
		ArrayList<pointer> cowsets= new ArrayList<pointer>();
		for(int i=0; i<N;i++){
			cowsets.add(new pointer());
		}
		
		//then//
		int lastweight = 0;
		int numcomponents = N;
		for(int i=0; i<edges.size();i++){
			edge e = edges.get(i);
			if(Find(cowsets.get(e.from))!=Find(cowsets.get(e.to))){
				Union(cowsets.get(e.from),cowsets.get(e.to));
				lastweight=e.len;
				numcomponents--;
				if(numcomponents==1){
					break;
				}
			}
			
			
			
		}
		
		out.println(lastweight);
		out.close();
		
		
	}
}
