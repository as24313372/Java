import java.util.*;

public class test1128 {
    public static void main(String[] args) {
    	sop("�g�c�j�py and x:");
        int [][] maze = new int [rint()+2][rint()+2];
        createmaze(maze);
        sop("\n");
        pmaze(maze);
        sop("\nstart(x):");
        int sx=rint();
        sop("start(y):");
        int sy=rint();
        sop("end(x):");
        int ex=rint();
        sop("end(y):");
        int ey=rint();
        maze[sy][sx]=5;
        maze[ey][ex]=6;
        pmaze(maze);
    }
    static void createmaze(int [][] m){
    	for(int i=0;i<m[0].length;i++){//��
    		m[0][i]=1;
    		m[m.length-1][i]=1;
    	}
    	for(int i=0;i<m.length;i++){//�C 
    		m[i][0]=1;
    		m[i][m[0].length-1]=1;
    	}
    	//random
    	sop("��K�צʤ���:");
        int ps=rint();
    	for(int i=1;i<m.length-1;i++){
    		for(int j=1;j<m[0].length-1;j++){
    			m[i][j]=((int)(Math.random()*100000)%100) > ps ? 0 : 1 ;
    		}
    	}
    }
    static void pmaze(int [][] m){
    	sop("\n");
    	int k=0;
    	for(int [] i:m){
    		System.out.printf("%2d",k++);
    		for(int j:i){
    			switch(j){
    				case 0:
    					sop("  ");//space
    					break;
    				case 1:
    					sop("�i");//wall
    					break;
    				case 5:
    					sop("��");
    					break;
    				case 6:
    					sop("�A");
    					break;
    			}
    		}
    		sop("\n");
    	}
    	sop("  ");
    	for(int i=0;i<m[0].length;i++){
    		System.out.printf("%2d",i);
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
