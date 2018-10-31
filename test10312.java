import java.util.*;

public class test10312 {
    public static void main(String[] args) {
        Scanner data= new Scanner(System.in);
        sop("請輸入高度：");
        int h = data.nextInt();
        sop("請輸入個數：");
        int count = data.nextInt();
        for(int i =0;i<count;i++){
        	for(int j=1;j<=h;j++){
        	//space
        		for(int k=1;k<j;k++)
        			sop(" ");
        	//print
        		for(int k=2*(h-j)+1;k>0;k--)
        			sop("*");
        	//space
        		for(int k=0;k<j;k++)
        			sop(" ");
        		sop("\n");
        	}
        	sop("\n");
        }
    }
    static void sop(String s){
    	System.out.print(s);
    }
}
