import java.util.Scanner;

public class test0403 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char num [] = sc.nextLine().split(" ").toCharArray();

 
        int count = 0;
        for(int i=0;i<num.length;i++){
        	if(i%2==0){
        		int a = num[i] - '0';
        		a *= 2;
        		if(a/10!=0){//¤G¦ì¼Æ
        			count += a/10;
        			count += a%10;
        		}else{
        			count += a;
        		}
        	}else{
        		int a = num[i] - '0';
        		count +=a;
        	}
        }
        if(count%10!=0)
        	System.out.print("Invalid");
        else
        	System.out.print("valid");	
    }
}