import java.util.*;

public class test1003 {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("�п�J�~���(ex:2018 9 26)�G");
		int year = sc.nextInt();
		int month = sc.nextInt();
		int day = sc.nextInt();
		int count=0;
		String w = "";
//-------------------------------------------------------------------
		if(year<1)
		{
			System.out.println("�~�����~");
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
										System.out.println("������~");
    									System.exit(0);
									}
							}
						else
							{
								if((day<1)|(day>30))
									{
										System.out.println("������~");
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
										System.out.println(" ������~");
										System.exit(0);
									}
							}
						else
							{
								if((day>28)|(day<1))
									{
										System.out.println(" ������~");
										System.exit(0);
									}
							}
					}
				}
			else
				{
					System.out.println("������~");
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
				case 4:w="�K";break;
				case 5:
				case 6: 	
				case 7:w="�L";break; 	
				case 8: 	
				case 9:	
				case 10:w="��";break; 	
				case 11:
				case 12:
				case 1:w="�V";break; 	
			}	
			count+= day;
			System.out.print(year+"�~"+month+"��"+day+"��O"+w+"�ѡA�ӥB�O");
			switch(count%7)
			{
				case 0:
					System.out.println("�P����");
					break;
				case 1:
					System.out.println("�P���@");
					break;
				case 2:
					System.out.println("�P���G");
					break;
				case 3:
					System.out.println("�P���T");
					break;
				case 4:
					System.out.println("�P���|");
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
    }
}
