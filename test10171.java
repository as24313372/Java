import java.util.*;

public class test10171 {

    public static void main(String[] args) {
        int [] B = new int [6];
        for(int i=0;i<6;i++)
        {
        	B[i]=(int)(Math.random()*49)+1;
        	for(int j=0;j<i;j++)
        	{
				if(B[i]==B[j])
				{
					i--;
					break;
				}
        	}
        }
        for(int i=0;i<6;i++)
        	System.out.print("第"+(i+1)+"個數是"+B[i]+"\n");
        Arrays.sort(B);
        for(int i=5;i>-1;i--)
        	System.out.print(B[i]+" ");
    }
}
