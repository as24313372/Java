import java.util.*;

public class test11281 {
    public static void main(String[] args) {
    	sop("n:");
    	int n = rint();
        int [][] ge = new int [n][n];//正方形
        a(ge);
        pmaze(ge);
        sop("\n");
        int [][] ge1 = new int [n][n*2];//長方形
        b(ge1);
        pmaze(ge1);
        sop("\n");
        int see=1;
        for(int i=0;i<n-1;i++)
        	see+=3;
        int [][] ge2 = new int [n][see];//梯形
        c(ge2);
        pmaze(ge2);
        sop("\n");
        int [][] ge3 = new int [2*n-1][2*n-1];//菱形
        d(ge3);
        pmaze(ge3);
        sop("\n");
    }
    //---------------------------------------------
    static void a(int [][] m){
    	for(int i=0;i<m[0].length;i++){
    		m[0][i]=1;
    		m[m.length-1][i]=1;
    	}
    	for(int i=0;i<m.length;i++){
    		m[i][0]=1;
    		m[i][m[0].length-1]=1;
    	}
    }
    //---------------------------------------------
    static void b(int [][] m){
    	for(int i=0;i<m[0].length;i++){
    		m[0][i]=1;
    		m[m.length-1][i]=1;
    	}
    	for(int i=0;i<m.length;i++){
    		m[i][0]=1;
    		m[i][m[0].length-1]=1;
    	}
    }
    //---------------------------------------------
    static void c(int [][] m){
    	int look=0;
    	for(int i=0;i<m.length-1;i++)
    		look+=1;
    	for(int i=look;i<=m[0].length-look-1;i++)//上
    		m[0][i]=1;
    	for(int i=0;i<m[0].length;i++)//下 
    		m[m.length-1][i]=1;
    	for(int i=1;i<m.length-1;i++){
    		m[i][look-i]=1;
    		m[i][m[0].length-1-look+i]=1;
    	}
    }
    //---------------------------------------------
    static void d(int [][] m){
    	int next=2;
    	for(int i=0;i<m.length;i++){
    		for(int j=0;j<m[i].length;j++){
    			if(i>m.length/2){
    				if(j==i-m.length/2)
    					m[i][j]=1;
    				if(j==m.length-next){
    					m[i][j]=1;
    					next+=1;
    				}
    			}
    			else{
    				if((j==m.length/2-i)||(j==m.length/2+i))
    					m[i][j]=1;
    			}
    		}
    	}
    }
    //---------------------------------------------
    static void pmaze(int [][] m){
    	for(int [] i:m){
    		for(int j:i){
    			switch(j){
    				case 0:
    					sop(" ");//space
    					break;
    				case 1:
    					sop("*");//wall
    					break;
    			}
    		}
    		sop("\n");
    	}
    }
    static int rint(){
    	Scanner data = new Scanner( System.in );
    	return data.nextInt();
    }
    static void sop(String s){
    	System.out.print(s);
    }
}