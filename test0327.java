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
    	sop("請輸入有幾筆資料：");int m = sc.nextInt();
    	score mate[] = new score[m];
    	for(int i=0;i<m;i++){
    		mate[i] = new score();
    		sop("請輸入學號：");
    		mate[i].num = sc.next();
    		sop("請輸入中文成績：");
    		mate[i].chi = sc.nextFloat();
    		sop("請輸入英文成績：");
    		mate[i].eng = sc.nextFloat();
    		sop("請輸入數文成績：");
    		mate[i].math = sc.nextFloat();
    		mate[i].pp=(mate[i].chi+mate[i].eng+mate[i].math)/3;
    	}
    	sop("----------------------------\n");
    	sop("NO\t中文\t英文\t數學\t平均\n");
    	for(int i=0;i<m;i++)
    		sop(mate[i].num + "\t" + mate[i].chi + "\t" + mate[i].eng + "\t" + mate[i].math + "\t" + mate[i].pp + "\n");
    	sop("----------------------------\n");
    }
    static void sop(String s){
    	System.out.print(s);
    }
}
