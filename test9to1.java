import java.util.*;

public class test9to1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt(),sumnum = in.nextInt(),count = 0;
		int list[][] = new int[num][2];//0-number 1-situation 0=+ 1=- 2=&
		for(int i=1;i<=num;i++)
			list[i-1][0] = i;//list[0]=1 list[1]=2... 0-8
		while(list[num-1][1] < 2){
			if(sum(num,list) == sumnum){//check ans
				System.out.println(print(num,list) + "=" + sum(num,list));
				count++;
			}
			
			list[0][1]++;//first object
			
			for(int i=0;i<num-1;i++){//check situation
				if((list[i][1] == 3)||(list[i][1] == 2 && list[i+1][1] == 2)){
					list[i][1] = 0;
					list[i+1][1]++;
				}
			}
			
		}
		//System.out.println(print(num,list) + "=" + sum(num,list));
		System.out.println(count);
		in.close();
	}
	public static int sum(int num,int list[][]){//sum for ans
		int a = 0,sum = 0,temp = 0;
		while(num > a){
			if(list[a][1] == 0){
				sum += (temp != 0)?temp:list[a][0];
				temp = 0;
			}else if(list[a][1] == 1){
				sum -= (temp != 0)?temp:list[a][0];
				temp = 0;
			}else if(list[a][1] == 2)
				temp = list[a+1][0] * 10 + list[a][0];
			a++;
		}
		return sum;
	}
	public static String print(int num,int list[][]){//print for show
		String str = "";
		while(num > 0){
			num--;
			if(list[num][1] == 0)
				str += "+" + list[num][0];
			else if(list[num][1] == 1)
				str += "-" + list[num][0];
			else if(list[num][1] == 2)
				str += "" + list[num][0];
		}
		return str;
	}
}

/*
98 - 76 + 54 + 3 + 21 = 100
9 - 8 + 76 + 54 - 32 + 1 = 100
98 + 7 + 6 - 5 - 4 - 3 + 2 - 1 = 100
98 - 7 - 6 - 5 - 4 + 3 + 21 = 100
9 - 8 + 76 - 5 + 4 + 3 + 21 = 100
98 - 7 + 6 + 5 + 4 - 3 - 2 - 1 = 100
98 + 7 - 6 + 5 - 4 + 3 - 2 - 1 = 100
98 + 7 - 6 + 5 - 4 - 3 + 2 + 1 = 100
98 - 7 + 6 + 5 - 4 + 3 - 2 + 1 = 100
98 - 7 + 6 - 5 + 4 + 3 + 2 - 1 = 100
98 + 7 - 6 - 5 + 4 + 3 - 2 + 1 = 100
98 - 7 - 6 + 5 + 4 + 3 + 2 + 1 = 100
9 + 8 + 76 + 5 + 4 - 3 + 2 - 1 = 100
9 + 8 + 76 + 5 - 4 + 3 + 2 + 1 = 100
9 - 8 + 7 + 65 - 4 + 32 - 1 = 100
-9 + 8 + 76 + 5-4 + 3 + 21 = 100
-9 + 8 + 7 + 65 - 4 + 32 + 1 = 100
-9-8 + 76 - 5 + 43 + 2 + 1 = 100
*/