import java.util.Scanner;

public class test26 {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("請輸入年月日(ex:2018 9 26)：");
		int year = sc.nextInt();
		int month = sc.nextInt();
		int day = sc.nextInt();
		int n=0;
		int count=0;
//-------------------------------------------------------------------
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
//-------------------------------------------------------------------
    		while (true)
			{
				if((year==1)||(n==year-1))
				{
					n=0;
					break;
				}
				else
					n++;
				if((n%400==0)||((n%100!=0)&&(n%4==0)))
					count+=366;
				else
					count+=365;
			}
			while(true)
			{
				if((month==1)||(n==month-1))
				{
					n=0;
					break;
				}
				else
					n++;
				if(n==2)
				{
       				if((year%400==0)||((year%100!=0)&&(year%4==0)))
						count+=29;
					else
						count+=28;
				}
				else
				{
					if((n==1)|(n==3)|(n==5)|(n==7)|(n==8)|(n==10)|(n==12))
						count+=31;
					else
						count+=30;
				}
			}
			count=day+count-1;
			System.out.println("是第"+count+"天");
			count=count%7;
			switch(count)
			{
				case 0:
					System.out.println("星期一");
					break;
				case 1:
					System.out.println("星期二");
					break;
				case 2:
					System.out.println("星期三");
					break;
				case 3:
					System.out.println("星期四");
					break;
				case 4:
					System.out.println("星期五");
					break;
				case 5:
					System.out.println("星期六");
					break;
				case 6:
					System.out.println("星期日");
					break;
				default:
					System.out.println("無法判斷");
			}
//-------------------------------------------------------------------
	}	
}

