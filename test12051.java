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
        		sop("�����\n");
        	else if(((a==b)&&(c==d))||((a==c)&&(b==d))||((b==c)&&(a==d)))
        		sop("�����\n");
        	else if(((a+b+c)<=d)||((a+b+d)<=c)||((a+c+d)<=b)||((b+c+d)<=a))
        		sop("�D�|���\n");
        	else
        		sop("�|���\n");
        }
    }
    static void sop(String s){
    	System.out.print(s);
    }
}
