import java.util.Scanner;

class score{
	int chi;
	int eng;
	int math;
}

class score2{
	score2(int m){
		chi = new int[m];
    	eng = new int[m];
    	math = new int[m];
	}
	int [] chi;
	int [] eng;
	int [] math;
}

public class test03274 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int m = sc.nextInt();
    	int n = sc.nextInt();
    	pline(n);
    	int A1[][] = new int[m][n];
    	pm(A1);
    	pline(n);
    	score a[] = new score[m];
    	for(int i=0;i<a.length;i++){
    		a[i] = new score();
    	}
    	pm1(a);
    	pline(n);
		score2 a1 = new score2(m);
		pm2(a1);
    	pline(n);
    }
    static void sop(String s){
    	System.out.print(s);
    }
    static void pm(int [][] M){
    	for(int m[] : M){
    		for(int n : m){
    			sop(n + " ");
    		}
    		sop("\n");
    	}
    }
    static void pm1(score [] M){
    	for(int i=0;i<M.length;i++){
    		sop(M[i].chi + " " + M[i].eng + " " + M[i].math + " ");
    		sop("\n");
    	}
    }
    static void pm2(score2 M){
    	for(int i=0;i<M.chi.length;i++){
    		sop(M.chi[i] + " ");	
    	}
    	sop("\n");
    	for(int i=0;i<M.eng.length;i++){
    		sop(M.eng[i] + " ");	
    	}
    	sop("\n");
    	for(int i=0;i<M.math.length;i++){
    		sop(M.math[i] + " ");	
    	}
    	sop("\n");
    }
    static void pline(int n){
    	for(int i=0;i<n*2-1;i++){
    		sop("-");
    	}
    	sop("\n");
    }
}
