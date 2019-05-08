import java.util.Scanner;
public class test05081 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int count = sc.nextInt();
    	for(int c=0;c<count;c++){
    		int sum = 0;
    		int num = sc.nextInt();
    		for(int i=1;i<num;i++){
    			if(num%i==0)
    				sum+=i;
    		}
    		if(sum==num)
    			System.out.println("perfect");
    		else if(sum < num)
    			System.out.println("deficient");
    		else
    			System.out.println("abundant");
    	}
	}
}