import java.util.*;

public class test10172 {

    public static void main(String[] args) {
        int [] B = new int [4];
        int sum=0;
        for(int i=0;i<4;i++)
        {
        	B[i]=(int)(Math.random()*6)+1;
        	sum+=B[i];
        	System.out.print("第"+(i+1)+"個數是"+B[i]+"\n");
        }
        System.out.print("總和="+sum+"\n");
        for(int i=1;i<7;i++)
        {
        	int count=0;
        	for(int j=0;j<4;j++)
        	{
        		if(B[j]==i)
        			count++;
        	}
        	if(count>1)
        		System.out.print("數字"+i+"重複"+count+"次");
        }
    }
}
