
public class tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x= 7;
		edit(x);
		System.out.println(x);
		
		String s = "ddd";
		sedit(s);
		System.out.println(s);
	}

	public static void edit(int x){
		x = 5;
	}
	
	public static void sedit(String s){
		s="ppp";
	}
}
