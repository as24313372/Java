import java.util.*;

public class test10242 {
    public static void main(String[] args) {
    	Scanner data = new Scanner(System.in);
    	System.out.print("�п�J��Ӿ��:");
    	int num1=data.nextInt();
    	int num2=data.nextInt();
    	System.out.print("��Ƴ̤j���]�Ƭ�:"+big(num1,num2));
    }
    static int big(int m,int n)
    {
    	if(m==0) return n;
    	if(n==0) return m;
    	if(m>n) return (big(m-n,n));
    	return (big(n-m,m));
    }
}
