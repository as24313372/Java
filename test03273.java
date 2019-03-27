import java.util.*;

public class test03273 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        float p = 1;
        p/=6;float q =1-p;
        float change = (float)(p * Math.pow(q,k-1)/(1-Math.pow(q,n)));
        System.out.printf("%.4f",change);
    }
}
