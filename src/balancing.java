import java.io.*;
import java.util.*;
public class balancing {
	public static class TreeHandler{
		public Tree root;
		public TreeHandler(Tree root){
			this.root = root;
		}
		
		public void delete(long check){
			if(root.height==check){
				if(root.left==null && root.right==null){
					
				}
				else{
					if(root.left==null){
						this.root = root.right;
						root.right.parent = root.right;
					}
					else{
						if(root.right==null){	
							root.left.parent=root.left;
							this.root = root.left;
						}
						else{
							root.delete(check);
						}
					}
				}
			}
			else{
				root.delete(check);
			}
		}
		
	}
	public static class Tree{
		Tree parent;
		Tree left;
		Tree right;
		int rightdescnum;
		cow thiscow;
		long height;
		public Tree(cow thiscow){
			this.parent = this;
			this.left = null;
			this.right = null;
			this.rightdescnum = 0;
			this.thiscow = thiscow;
			this.height = thiscow.yLoc;
		}
		public void add(Tree t){
			if(this.height>=t.height){
				if(this.left==null){
					this.left = t;
					t.parent = this;
				}
				else{
					this.left.add(t);
				}
			}
			if(this.height<t.height){
				if(this.right==null){
					this.right = t;
					t.parent = this;
				}
				else{
					this.right.add(t);
				}
				rightdescnum++;
			}

		}
		public long findGreater(long order){
			int numGreater = 0;
			if(order>height){
				if(right!=null)
				{
					numGreater+=right.findGreater(order);
				}
			}
			if(order==height){
				numGreater+=rightdescnum;
			}
			if(order<height){
				numGreater=numGreater+rightdescnum+1;
				if(left!=null){
					numGreater+=left.findGreater(order);
					
				}
			}
		
			return numGreater;
		}
		public void inOrder(){
			if(this.left!=null){
				left.inOrder();
			}
			System.out.println(height+" "+rightdescnum +" and this has parent"+this.parent.height);
			if(this.right!=null){
				right.inOrder();
			}
		}
		public void delete(long check){
			if(height==check){
				if(left==null && right==null){
					if(this.parent.height>=this.height){
						
						this.parent.left=null;
					}
					else{
						this.parent.right=null;
					}
					this.parent = null;
				}
				else{
					if(left==null){
						if(this.parent.height>=this.height){
							this.parent.left=right;
						}
						else{
							this.parent.right=right;
						}
						this.right.parent = this.parent;
						this.parent = null;
					}
					else{
						if(right==null){
							if(this.parent.height>=this.height){
								this.parent.left=left;
							}
							else{
								this.parent.right=left;
							}					
							this.left.parent = this.parent;
							this.parent = null;
						}
						else{
							Tree temp = this.right.findMin();
							long tempheight = temp.height;
							this.delete(temp.height);
							this.height=tempheight;
						}
	
					}					
				}
				
			}
			
			else{
				if(check>=height){
					rightdescnum--;
					right.delete(check);
				}
				if(check<height){
					left.delete(check);
				}

			}
			
			
		}
		
		public Tree findMin(){
			if(this.left==null){
				return this;
			}
			else{
				return this.left.findMin();
			}
			
			
		}
	
	}
	public static class cow implements Comparable<cow>{
		public int xLoc;
		public int yLoc;
		public cow(int xLoc, int yLoc){
			this.xLoc = xLoc;
			this.yLoc = yLoc;
		}
		@Override
		public int compareTo(cow o) {
			return this.yLoc-o.yLoc;
		}
		
		
		
		
	}
	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("balancing.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());

		 int N = Integer.parseInt(st.nextToken());
		 cow[] cows = new cow[N];
		

		 StringTokenizer pp = new StringTokenizer(f.readLine());
		 cows[0]=new cow(Integer.parseInt(pp.nextToken()) , Integer.parseInt(pp.nextToken()) );
		 Tree rightCows = new Tree(cows[0]);
		 Tree leftCows = new Tree(cows[0]);
		 TreeHandler leftHandler = new TreeHandler(leftCows);
		 TreeHandler rightHandler = new TreeHandler(rightCows);
		 
		 for(int i=1; i<N;i++){
			 StringTokenizer p = new StringTokenizer(f.readLine());
			 cows[i]=new cow(Integer.parseInt(p.nextToken()) , Integer.parseInt(p.nextToken()) );
			 rightCows.add(new Tree(cows[i]));
		 }
		 
		 Arrays.sort(cows, new Comparator<cow>() {
			    public int compare(cow o1, cow o2) {
			        return o1.xLoc-o2.xLoc;
			    }
			});
		 for(int i=0; i<cows.length;i++){
			 System.out.println(cows[i].xLoc+" "+cows[i].yLoc);
		 }
		 
		 
		 
		 
		 
		 //use forloops to add cows to sweepline//
	}

}
