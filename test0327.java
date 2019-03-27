import java.util.Scanner;

class score{
	String num;
	float chi;
	float eng;
	float math;
	float pp;
}

public class test0327 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	System.out.print("請輸入有幾筆資料：");int m = sc.nextInt();
    	score mate[] = new score[m];
    	for(int i=0;i<m;i++){
    		mate[i] = new score();
    		System.out.print("請輸入學號：");
    		mate[i].num = sc.next();
    		System.out.print("請輸入中文成績：");
    		mate[i].chi = sc.nextFloat();
    		System.out.print("請輸入英文成績：");
    		mate[i].eng = sc.nextFloat();
    		System.out.print("請輸入數文成績：");
    		mate[i].math = sc.nextFloat();
    		mate[i].pp=(mate[i].chi+mate[i].eng+mate[i].math)/3;
    	}
    	System.out.println("----------------------------");
    	System.out.print("NO\t中文\t英文\t數學\t平均\n");
    	for(int i=0;i<m;i++)
    		System.out.printf("%2s  %.1f  %.1f  %.1f  %.1f\n",mate[i].num,mate[i].chi,mate[i].eng,mate[i].math,mate[i].pp);
    	System.out.println("----------------------------");
    }
}
