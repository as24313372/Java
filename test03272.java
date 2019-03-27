import java.util.Scanner;

public class test03272 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int m = sc.nextInt();int n = sc.nextInt();
    	int score = 0;
    	if(m>n){
    		int change = m;
    		m = n;
    		n = change;
    	}
    	for(int i=m;i<=n;i++){
    		if(i%2!=0)
    			score += i;
    	}
    	System.out.print(score);
    }
}
