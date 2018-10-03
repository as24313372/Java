import java.util.*;

public class test1003 {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("請輸入年月日(ex:2018 9 26)：");
		int year = sc.nextInt();
		int month = sc.nextInt();
		int day = sc.nextInt();
		int count=0;
		String w = "";
//-------------------------------------------------------------------
		if(year<1)
		{
			System.out.println("年份錯誤");
    		System.exit(0);
		}
		else
		{
			if((month>0)&(month<13))
				{
				if(month!=2)	
					{		
						if((month==1)|(month==3)|(month==5)|(month==7)|(month==8)|(month==10)|(month==12))
							{
								if((day<1)|(day>31))
									{
										System.out.println("日期錯誤");
    									System.exit(0);
									}
							}
						else
							{
								if((day<1)|(day>30))
									{
										System.out.println("日期錯誤");
    									System.exit(0);
									}
							}
					}
				else
					{
						if ((year%400==0)||((year%100!=0)&&(year%4==0)))
							{
								if((day>29)|(day<1))
									{
										System.out.println(" 日期錯誤");
										System.exit(0);
									}
							}
						else
							{
								if((day>28)|(day<1))
									{
										System.out.println(" 日期錯誤");
										System.exit(0);
									}
							}
					}
				}
			else
				{
					System.out.println("月份錯誤");
					System.exit(0);
				}
		}
//-------------------------------------------------------------------
    		count+=(year-1) * 365 + (year-1) / 4 - (year-1) / 100 + (year-1) / 400;	
			switch(month-1)
			{
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
			switch(month)
			{
				case 2:
				case 3:	
				case 4:w="春";break;
				case 5:
				case 6: 	
				case 7:w="夏";break; 	
				case 8: 	
				case 9:	
				case 10:w="秋";break; 	
				case 11:
				case 12:
				case 1:w="冬";break; 	
			}	
			count+= day;
			System.out.print(year+"年"+month+"月"+day+"日是"+w+"天，而且是");
			switch(count%7)
			{
				case 0:
					System.out.println("星期日");
					break;
				case 1:
					System.out.println("星期一");
					break;
				case 2:
					System.out.println("星期二");
					break;
				case 3:
					System.out.println("星期三");
					break;
				case 4:
					System.out.println("星期四");
					break;
				case 5:
					System.out.println("星期五");
					break;
				case 6:
					System.out.println("星期六");
					break;
				default:
					System.out.println("無法判斷");
			}
    }
}
