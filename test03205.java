import java.util.Scanner;

public class test03205 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        double p = 0.16666, q = 1 - p, allfail = 1;
        for(int i = 0; i < n; i++){
            if(i < k - 1) p *= q;
            allfail *= q;
        }
        double ans = 0;
        int count = 0;
        while(p > 1e-9){
        	count++;
            ans += p;
            p *= allfail;
        }
        System.out.printf("%.4f %d",ans,count);
    }
}
