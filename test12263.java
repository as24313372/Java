import java.util.*;
public class test12263 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int m = sc.nextInt();
    	int n = sc.nextInt();
    	s(m , n);
    }
    static void s(int m , int n){
    	if(m < n){
    		System.out.print(m + " ");
    		s(++m , n);
    	}
    	else if(m > n){
    		System.out.print(n + " ");
    		s(++n , m);
    	}
    	else
    		System.out.print(n + " ");
    }
}
