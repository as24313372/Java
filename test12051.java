import java.util.*;

public class test12051 {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        int n = data.nextInt();
        for(int i=0;i<n;i++){
        	int a = data.nextInt();
        	int b = data.nextInt();
        	int c = data.nextInt();
        	int d = data.nextInt();
        	if((a==b)&&(a==c)&&(a==d))
        		sop("正方形\n");
        	else if(((a==b)&&(c==d))||((a==c)&&(b==d))||((b==c)&&(a==d)))
        		sop("長方形\n");
        	else if(((a+b+c)<=d)||((a+b+d)<=c)||((a+c+d)<=b)||((b+c+d)<=a))
        		sop("非四邊形\n");
        	else
        		sop("四邊形\n");
        }
    }
    static void sop(String s){
    	System.out.print(s);
    }
}
