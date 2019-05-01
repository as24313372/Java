import java.util.Scanner;

public class test0501 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numcheck = 0;
        int number [] = new int [10];
        int numbercheck [] = new int [10];
        for(int i=0;i<10;i++){
        	number[i] = 0;
        	numbercheck[i] = 0;
        }
        while(true){
        	int num = sc.nextInt();
        	if(num==0)break;sc.nextLine();sc.next();
        	String check = sc.next();
        	/*
        	if(check == "high"){
        		for(int i=num;i<10;i++)
        			number[i] = 1;
        	}else if(check == "low"){
        		for(int i=num;i>0;i--)
        			number[i] = 1;
        	}else if(check == "on"){
        		if(number[num] == 1)
        			System.out.println("Stan is dishonest");
        		else
        			System.out.println("Stan may be honest");
        		for(int i=0;i<10;i++)
        			number[i] = 0;
        	}else{
        		System.out.println("error ans");
        	}*/
        	switch(check){
        		case "high":
        			for(int i=num-1;i<10;i++)
        				number[i] = 1;
        			break;
        		case "low":
        			for(int i=num-1;i>=0;i--)
        				number[i] = 1;
        			break;
        		case "on":
        			if(number[num-1] == 1)
        				numbercheck[numcheck] = 1;
        			else
        				numbercheck[numcheck] = 2;
        			numcheck += 1;
        			for(int i=0;i<10;i++)
        				number[i] = 0;
        			break;
        		default:
        			System.out.println("error ans");
        	}
        }
        for(int i=0;i<10;i++){
        	if(numbercheck[i] == 1)
        		System.out.println("Stan is dishonest");
        	if(numbercheck[i] == 2)
        		System.out.println("Stan may be honest");
        }
    }
}
