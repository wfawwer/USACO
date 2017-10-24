import java.io.*;
import java.util.*;
public class bphoto {
	
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
		long height;
		public Tree(long height){
			this.parent = this;
			this.left = null;
			this.right = null;
			this.rightdescnum = 0;
			this.height = height;
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
	public static void main(String[] args)  throws IOException{
		 BufferedReader f = new BufferedReader(new FileReader("bphoto.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
		 StringTokenizer st = new StringTokenizer(f.readLine());
		 
		 int N = Integer.parseInt(st.nextToken());
		 StringTokenizer p = new StringTokenizer(f.readLine());
		 Tree rightt = new Tree(Long.parseLong(p.nextToken()));
		 TreeHandler righthandler = new TreeHandler(rightt);
		 Tree leftt = new Tree(rightt.height);
		 TreeHandler lefthandler = new TreeHandler(leftt);
		 long[] order= new long[N];
		 order[0]=rightt.height;
		 for(int i=1; i<N;i++){
			 StringTokenizer pp = new StringTokenizer(f.readLine());
			 long k =Long.parseLong(pp.nextToken());
			 rightt.add(new Tree(k));
			 order[i]=k;
		 }
		
		 int numUnbalanced = 0;
		 int[] regCheck = new int[N];
		 //handle left case//

		 long firstrightHigher = righthandler.root.findGreater(order[0]);
		 long firstleftHigher = lefthandler.root.findGreater(order[0]);
		 if(firstleftHigher>2*firstrightHigher || firstrightHigher>2*firstleftHigher){
			 numUnbalanced++;
			 regCheck[0]=1;
		 }
		 righthandler.root.delete(order[0]);
		 
		 for(int i=1;i<N-1;i++){
			 lefthandler.root.add(new Tree(order[i]));
			 long rightHigher = righthandler.root.findGreater(order[i]);
			 long leftHigher = lefthandler.root.findGreater(order[i]);
			 if(leftHigher>2*rightHigher || rightHigher>2*leftHigher){
				 numUnbalanced++;
				 regCheck[i]=1;
			 }
			 righthandler.delete(order[i]);
		 }
		 //handle right case//
		 
		 lefthandler.root.add(new Tree(order[N-1]));
		 long lastrightHigher = righthandler.root.findGreater(order[N-1]);
		 long lastleftHigher = lefthandler.root.findGreater(order[N-1]);
		 if(lastleftHigher>2*lastrightHigher || lastrightHigher>2*lastleftHigher){
			 numUnbalanced++;
			 regCheck[N-1]=1;
		 }
		 
		 out.println(numUnbalanced);
		 out.close();
			 
	}
	
	
}
