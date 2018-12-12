import java.util.*;

public class test1212 {
	static int cx = 0 , cy = 0 , dir = 0,count=0,ind=0;
	
    public static void main(String[] args) {
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
        mouse(maze,sx,sy);
    }
    static void createmaze(int [][] m){
    	for(int i=0;i<m[0].length;i++){//行
    		m[0][i]=1;
    		m[m.length-1][i]=1;
    	}
    	for(int i=0;i<m.length;i++){//列 
    		m[i][0]=1;
    		m[i][m[0].length-1]=1;
    	}
    	//random
    	sop("牆密度百分比:");
        int ps=rint();
    	for(int i=1;i<m.length-1;i++){
    		for(int j=1;j<m[0].length-1;j++){
    			m[i][j]=((int)(Math.random()*100000)%100) >= ps ? 0 : 1 ;
    			count+=(m[i][j] == 1 ? 1 : 0); 
    		}
    	}
    }
    static void mouse(int [][] m , int sx,int sy){
    	int [][] stor = new int [(m.length-2)*(m[0].length-2)][3];
    	cy=sy;
    	cx=sx;
    	while(true){
    		if(m[cy][cx]==6){
				sop("\nGood Job,We got it");
				return;
    		}
    		else
    			m[cy][cx]=3;
    		pmaze(m);
    		switch(dir){
    			case 0://right0
    				if((m[cy][cx+1] == 0)||(m[cy][cx+1] == 6)){
    					m[cy][cx ] = 4;
    					stor[ind][0]=cy;
    					stor[ind][1]=cx;
    					stor[ind++][2]=dir;
    					cx+= 1;
    					dir = 0;
    				}
    				else{
    					dir++;
    				}
    				break;
    			case 1://down1
    				if((m[cy+1][cx] == 0)||(m[cy+1][cx] == 6)){
    					m[cy][cx] = 4;
    					stor[ind][0]=cy;
    					stor[ind][1]=cx;
    					stor[ind++][2]=dir;
    					cy+= 1;
    					dir = 0;
    				}
    				else{
    					dir++;
    				}
    				break;
    			case 2://left2
    				if((m[cy][cx-1] == 0)||(m[cy][cx-1] == 6)){
    					m[cy][cx] = 4;
    					stor[ind][0]=cy;
    					stor[ind][1]=cx;
    					stor[ind++][2]=dir;
    					cx-= 1;
    					dir = 0;
    				}
    				else{
    					dir++;
    				}
    				break;
    			case 3://up3
    				if((m[cy-1][cx] == 0)||(m[cy-1][cx] == 6)){
    					m[cy][cx] = 4;
    					stor[ind][0]=cy;
    					stor[ind][1]=cx;
    					stor[ind++][2]=dir;
    					cy-= 1;
    					dir = 0;
    				}
    				else{
    					dir++;
    				}
    				break;
    			default:
    				if(--ind < 0){
    					sop("\ndead mouse");
    					return;
    				}
    				m[cy][cx]=7;
    				cy=stor[ind][0];
    				cx=stor[ind][1];
    				dir=stor[ind][2];
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
    					sop("█");//wall
    					break;
    				case 3:
    					sop("＠");//mouse
    					break;
    				case 4:
    					sop("。");//road
    					break;
    				case 5:
    					sop("ｏ");
    					break;
    				case 6:
    					sop("ｘ");
    					break;
    				case 7:
    					sop("、");
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