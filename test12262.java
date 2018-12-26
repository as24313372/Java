import java.util.*;
public class test12262 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int m=sc.nextInt();
		int n=sc.nextInt();
		int items=sc.nextInt();
		for(int line = 1 ; line <= n ; line++){
			if(line < m) continue;
			for(int item = 1 ; item <= items ; item++){
				for(int space = 1 ; space <= n-line ; space++){
					System.out.print(" ");
				}
				for(int star=1;star<=(line*2-1);star++){
					if(line == m || star == 1|| star == 2*line-1 || line == n)System.out.print("*");
					else System.out.print("$");
				}
				for(int space = 1 ; space <= n-line ; space++){
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}
}