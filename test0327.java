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
    	sop("�п�J���X����ơG");int m = sc.nextInt();
    	score mate[] = new score[m];
    	for(int i=0;i<m;i++){
    		mate[i] = new score();
    		sop("�п�J�Ǹ��G");
    		mate[i].num = sc.next();
    		sop("�п�J���妨�Z�G");
    		mate[i].chi = sc.nextFloat();
    		sop("�п�J�^�妨�Z�G");
    		mate[i].eng = sc.nextFloat();
    		sop("�п�J�Ƥ妨�Z�G");
    		mate[i].math = sc.nextFloat();
    		mate[i].pp=(mate[i].chi+mate[i].eng+mate[i].math)/3;
    	}
    	sop("----------------------------\n");
    	sop("NO\t����\t�^��\t�ƾ�\t����\n");
    	for(int i=0;i<m;i++){
    		sop(mate[i].num + "\t" + mate[i].chi + "\t" + mate[i].eng + "\t" + mate[i].math + "\t" + mate[i].pp + "\n");
    	}
    	sop("----------------------------\n");
    }
    static void sop(String s){
    	System.out.print(s);
    }
}
