
import java.util.*;
import java.io.*;
public class closing {
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
		 BufferedReader f = new BufferedReader(new FileReader("closing.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 int N = Integer.parseInt(st.nextToken());
		 int M = Integer.parseInt(st.nextToken());
		 ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
		 for(int i=0; i<N;i++){
			 edges.add(new ArrayList<Integer>());
		 }
		 for(int i=0; i<M;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 int from = Integer.parseInt(p.nextToken());
			 int to = Integer.parseInt(p.nextToken());
			 edges.get(from-1).add(to-1);
			 edges.get(to-1).add(from-1);
		 }

		 ArrayList<Integer> farmorder = new ArrayList<Integer>();
		 for(int i=0; i<N;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 farmorder.add(Integer.parseInt(p.nextToken()));
		 }
		 int[] components = new int[N];
		 pointer[] farms = new pointer[N];
	
		 int numComp = 0;
		 for(int i=0; i<N;i++){
			 int farmID = farmorder.get(N-1-i)-1;
			// System.out.println("the farm ID is "+farmID);
			 farms[farmID]= new pointer();
			 numComp++;
			 for(int j=0; j<edges.get(farmID).size();j++){
				// System.out.println("there is an edge to"+edges.get(farmID).get(j));
				 if(farms[edges.get(farmID).get(j)]!=null){
					 //System.out.println("its not null");
					 if(Find(farms[farmID])!=Find(farms[edges.get(farmID).get(j)])){
						 Union(farms[farmID],farms[edges.get(farmID).get(j)]);
						 numComp--;
					 }
				 }
			 }	 			 
			 components[N-1-i]=numComp;
		 }
		 for(int i=0; i<components.length;i++){
			 if(components[i]==1){
				out.println("YES"); 
			 }
			 else{
				 out.println("NO");
			 }
		 }
		 out.close();
		 

		 
	}
}
