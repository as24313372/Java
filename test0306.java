import java.util.*;

public class test0306 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		char [] num = sc.nextLine().toCharArray();
		int a = 0,b = 0;
		for(int j=0;j<num.length;j++){
			if(num[j]=='O')
				a+=++b;
			if(num[j]=='X')
				b = 0;
		}
		sop(a + "\n");
    }
    static void sop(String s){
    	System.out.print(s);
    }
}
