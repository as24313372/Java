import java.util.Scanner;

public class test0403 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cardnum = sc.nextLine().replace(" ","");
        char num [] = cardnum.toCharArray();
        int count = 0;
        for(int i=0;i<num.length;i++){
        	if(i%2==0){
        		int a = num[i] - '0';
        		a *= 2;
        		count +=(a/10!=0)?(a/10+a%10):a;
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
