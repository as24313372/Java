import java.util.Scanner;
class area{
	area(int xh){
		x = new int[xh]; 
	}
	int [] x;
}

public class test0424 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int x = sc.nextInt();
    	int y = sc.nextInt();
    	int ps = sc.nextInt();
		area maze []= new area[y+2];
		for(int i=0;i<y+2;i++)
			maze[i]=new area(x+2);
		setmaze(maze,ps);
		pmaze(maze);
	}
	static void setmaze(area maze [],int ps){
		for(int i=0;i<maze.length;i++){
			for(int j=0;j<maze[i].x.length;j++){
				if(i==0 || i==maze.length-1 || j==0 || j==maze[i].x.length-1){
					maze[i].x[j] = 1;
					continue;
				}else{
					maze[i].x[j] = ((int)(Math.random()*100000)%100) >= ps ? 0 : 1 ;	
				}
			}
		}
	}
	static void pmaze(area maze []){
		for(int i=0;i<maze.length;i++){
			for(int j=0;j<maze[i].x.length;j++){
				switch(maze[i].x[j]){
					case 0:
						sop("  ");
						break;
					case 1:
						sop("¢i");
						break;
				}
			}
			sop("\n");
		}
	}
	static void sop(String s){
		System.out.print(s);
	}
}