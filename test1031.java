import java.util.*;

public class test1031 {
    public static void main(String[] args) {
		 data("����ģ",1);
		 data("�|�T�@��",2);
		 data("18",3);
    }
    static void sop(String s){
    	System.out.print(s);
    }
    static void data(String s,int i){
    	switch(i){
    		case 1:
    			sop("�m�W�G"+s+" ");
    			break;
    		case 2:
    			sop("�Z�šG"+s+" ");
    			break;
    		case 3:
    			sop("�y���G"+s+" ");
    			break;
    		default:
    			sop("�ƭȿ��~");
    	}
    }
 }
