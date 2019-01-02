import java.util.*;

public class test0102 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	while(true){
    		int line = sc.nextInt();
    		if(line == 0)break;
    		p5(line);	
    		sop("\n");

    	}
    		
    }
    static void sop(String s){
    	System.out.print(s);
    }
    static void p5(int line){
    	if(line == 1)
    		line++;
    	else if(line>3)
    		line-=line/2-1;
    	for(int k=1;k<=2*line-1;k++)
    		sop("X");
    	for(int k=1;k<line;k++){
    		sop("\n");
    		for(int j=1;j<=line-k;j++)
    			sop("X");
    		for(int j=1;j<=2*k-1;j++)
    			sop("O");
    		for(int j=1;j<=line-k;j++)
    			sop("X");
    	}
    	for(int k=line-2;k>=1;k--){
    		
    		sop("\n");
    		for(int j=1;j<=line-k;j++)
    			sop("X");
    		for(int j=1;j<=2*k-1;j++)
    			sop("O");
    		for(int j=1;j<=line-k;j++)
    			sop("X");
    	}
    	sop("\n");
    	for(int k=1;k<=2*line-1;k++)
    		sop("X");
    }	
}

