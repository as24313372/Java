import java.util.Scanner;

public class test26 {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("�п�J�~���(ex:2018 9 26)�G");
		int year = sc.nextInt();
		int month = sc.nextInt();
		int day = sc.nextInt();
		int n=0;
		int count=0;
//-------------------------------------------------------------------
		if((month>0)&(month<13))
		{
			if((month==1)|(month==3)|(month==5)|(month==7)|(month==8)|(month==10)|(month==12))
			{
				if((day<0)|(day>31))
				{
					System.out.println("������~");
    				System.exit(0);
				}
    		}
    		else
    		{
    			if((day<0)|(day>30))
				{
					System.out.println("������~");
    				System.exit(0);
				}
    		}
    	}
    	else
    	{
    		System.out.println("�õL�����");
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
			System.out.println("�O��"+count+"��");
			count=count%7;
			switch(count)
			{
				case 0:
					System.out.println("�P���@");
					break;
				case 1:
					System.out.println("�P���G");
					break;
				case 2:
					System.out.println("�P���T");
					break;
				case 3:
					System.out.println("�P���|");
					break;
				case 4:
					System.out.println("�P����");
					break;
				case 5:
					System.out.println("�P����");
					break;
				case 6:
					System.out.println("�P����");
					break;
				default:
					System.out.println("�L�k�P�_");
			}
//-------------------------------------------------------------------
	}	
}

