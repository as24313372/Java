import java.util.*;

public class test1024 {
    public static void main(String[] args) {
    	int ans;
    	Scanner data = new Scanner(System.in);
    	System.out.print("�п�J��ƭӼ�:");
    	int count=data.nextInt();
    	if((count>100)|(count<1))
    	{
    		System.out.print("�ӼƤ���W�L100�Τp��1");
    		System.exit(0);
    	}
    	int [] num=new int[count];
    	for(int i=0;i<num.length;i++)
    	{
    		num[i]=(int)(Math.random()*1000+1);
    		System.out.print(num[i]+" ");
    	}
    	if(count%2==0)
    		ans=(num[count/2]+num[count/2-1])/2;
    	else
    		ans=num[count/2];
    	System.out.print("\n"+"�����="+ans);
    }
}