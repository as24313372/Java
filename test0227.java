import java.util.Scanner;

public class test0227 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        for(int i=0;i<a;i++){
        	int b = sc.nextInt();
        	int num [] = new int[b];
        	for(int j=0;j<b;j++)
        		num[j]=sc.nextInt();
        	int c = 0;
        	for(int j=1;j<b;j++){
        		if(num[j-1]>num[j]){
        			int d = num[j-1];
        			num[j-1] = num[j];
        			num[j] = d;
        			c++;
        			j=0;
        		}
        	}
        	System.out.println("\nOptimal train swapping takes "+ c +" swaps.");
        }
    }
    static void sop(String s){
    	System.out.print(s);
    }
}