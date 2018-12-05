import java.util.*;

public class test1205 {
    public static void main(String[] args) {
        Scanner line = new Scanner(System.in);
        int a = line.nextInt();
        int b = line.nextInt();
        int c = line.nextInt();
        if((a==c)&&(a==b))
        	sop("正三角形");
        else if((a+b>c)&&(a+c>b)&&(b+c>a)){
        	if((a==b)||(a==c)||(b==c))
        		sop("等腰三角形");
        	else
        		sop("一般三角形");
        }
        else
        	sop("非三角形");
    }
    static void sop(String s){
    	System.out.print(s);
    }
}
