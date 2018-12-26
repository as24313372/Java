import java.util.*;

public class test1226 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
    	int n = sc.nextInt();
    	int map[][] = new int [m][n];
    	snake(map);
    }
    static void sop(String s){
    	System.out.print(s);
    }
    static void snake(int [][] map){
    	int m=map.length-1;
    	int n=map[0].length-1;
    	for(int i=n;i>=0;i--){
    		if(i%2==0){
    			for(int j=m;j>=0;j--)
    				sop("["+j+","+i+"]");
    		}
    		else{
    			for(int j=0;j<=m;j++)
    				sop("["+j+","+i+"]");
    		}
    		sop("\n");
    	}
    }
}
