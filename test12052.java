import java.util.*;

public class test12052 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int rx=sc.nextInt();//area
		int ry=sc.nextInt();//area
		int [][] area = new int[51][51];
		for(int i=0;i<rx;i++){		//area
			for(int j=0;j<ry;j++)
				area[i][j]=0;
		}
		int x = sc.nextInt();	//y
		int y = sc.nextInt();	//x
		String dar = sc.next();	//方向
		area[y][x]=1;
		String [] n = sc.nextLine().split();
		for(int i=0;i<n.length;i++){
			if(n[i]=="F"){
				switch(dar){
					case "N"://y++
						
					case "S"://y--
						
					case "E"://x++
						
					case "W"://x--
						
				}
			}
			else if((n[i]=="R")||(n[i]=="L")){
				dar = dis(dar,n[i]);
			}
		}
	}
	static char dis(char m,char n){//m 原方向 n 指令 北N南S東E西W
		switch(n){
			case "R":
				switch(m){
					case "N":
						return "E";
					case "S":
						return "W";
					case "W":
						return "N";
					case "E":
						return "S";
				}
				break;
			case "L":
				switch(m){
					case "N":
						return "W";
					case "S":
						return "E";
					case "W":
						return "S";
					case "E":
						return "N";
				}
				break;
		}
	}
}