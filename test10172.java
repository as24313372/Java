import java.util.*;

public class test10172 {

    public static void main(String[] args) {
        int [] B = new int [4];
        int sum=0;
        for(int i=0;i<4;i++)
        {
        	B[i]=(int)(Math.random()*6)+1;
        	sum+=B[i];
        }
        for(int i=0;i<4;i++)
        {
        	System.out.print("��"+(i+1)+"�ӼƬO"+B[i]+"\n");
        }
        System.out.print("�`�M="+sum+"\n");
        for(int i=1;i<7;i++)
        {
        	int count=0;
        	for(int j=0;j<4;j++)
        	{
        		if(B[j]==i)
        			count++;
        	}
        	if(count>1)
        		System.out.print("�Ʀr"+i+"����"+count+"��");
        }
    }
}
