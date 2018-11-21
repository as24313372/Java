import java.util.Scanner;//import Scanner用來掃描使用者輸入

public class test1121plus {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);//增加一個scanner空間名為data
        String num[] = data.nextLine().split(" ");//掃描使用者打的那一行並且用空格分割到num陣列裡
        switch(num.length){//判斷list裡有幾個元素
        	case 1:		   //先利用num.length來知道使用者共打了幾個，在判斷是要跑哪個overload
        		juice(num[0]);
        		break;
        	case 2:
        		juice(num[0],num[1]);
        		break;
        	case 3:
        		juice(num[0],num[1],num[2]);
        		break;
        }
    }
    //副程式用OVERLOAD來跑
    static void juice(String s1){
    	System.out.print("我要一杯"+s1+"juice");
    }
    static void juice(String s1,String s2){
    	System.out.print("我要"+s1+"杯"+s2+"juice");
    }
    static void juice(String s1,String s2,String s3){
    	System.out.print("我要"+s1+"杯"+s2+"的"+s3+"juice");
    }
}
