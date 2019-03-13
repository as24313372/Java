import java.util.*;

public class test0313 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int num = sc.nextInt();//num>=4
    	int anum []= new int[num];
    	for(int i=0;i<anum.length;i++)anum[i]=0;
    	int count = 0;
    	for(int i=2;i<num;i++){
    		for(int j=2;j<=i;j++){
    			if(i==j)
    				anum[count++]=i;
    			if(i%j==0)break;
    		}
    	}
    	count = 0;
    	for(int i=0;i<anum.length;i++){
    		if(anum[i] == 0)continue;
    		for(int j=0;j<anum.length;j++){
    			if(anum[j] == 0)continue;
    			if(anum[j] + anum[i] == num){
    				anum[i] = 0;
    				count++;
    			}
    		}
    	}
    	System.out.println(count);
    }
    
}
