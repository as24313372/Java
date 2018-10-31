import java.util.*;

public class test1031 {
    public static void main(String[] args) {
		 data("李光耀",1);
		 data("四訊一甲",2);
		 data("18",3);
    }
    static void sop(String s){
    	System.out.print(s);
    }
    static void data(String s,int i){
    	switch(i){
    		case 1:
    			sop("姓名："+s+" ");
    			break;
    		case 2:
    			sop("班級："+s+" ");
    			break;
    		case 3:
    			sop("座號："+s+" ");
    			break;
    		default:
    			sop("數值錯誤");
    	}
    }
 }
