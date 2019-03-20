import java.util.Scanner;

public class test03204 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int num[][] = new int[n][];
        for(int i=0;i<n;i++){
        	num[i] = new int[i+1];
        	for(int j=0;j<num[i].length;j++){
        		num[i][0]=1;
        		num[i][num[i].length-1]=1;
        		if(j!=0&&j!=num[i].length-1){
        			num[i][j]=num[i-1][j]+num[i-1][j-1];
        		}
        	}
        }
        for(int i=0;i<num.length;i++){
        	for(int j=0;j<num[i].length;j++){
        		System.out.print(num[i][j]+" ");
        	}
        	System.out.println();
        }
    }
}
