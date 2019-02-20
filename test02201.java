import java.util.*;

public class test02201 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int count = 0;
    	int year = sc.nextInt();
    	int month = sc.nextInt();
    	int day = sc.nextInt();
        switch(month-1){
				case 11: count+=30;
				case 10: count+=31;
				case 9: count+=30;
				case 8: count+=31;
				case 7: count+=31;
				case 6: count+=30;
				case 5: count+=31;
				case 4: count+=30;
				case 3: count+=31;
				case 2: count+=((year%400==0)||((year%100!=0)&&(year%4==0)))? 29 : 28;
				case 1: count+=31;
		}
		count+=day;
		System.out.print(count);
    }
}