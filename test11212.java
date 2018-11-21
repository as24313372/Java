public class test11212 {
    public static void main(String[] args) {
        int [] B = new int [6];//建立一個陣列名為B，且裡面只能6個元素
        int sum=0;//初始值用來表示總合
        for(int i=0;i<6;i++){//產生6個亂數
        	B[i]=(int)(Math.random()*6)+1;//產生亂數在存入B lsit第i個位置
        	sum+=B[i];//加乘總合
        	System.out.print("第"+(i+1)+"個數是"+B[i]+"\n");//輸出第i位的亂數值
        }
        System.out.print("總和="+sum+"\n");//輸出總合
        for(int i=1;i<7;i++){//判斷B list裡是否有重複超過1次以上的亂數
        	int count=0;//記數
        	for(int j=0;j<6;j++){//從1-6判斷此數
        		if(B[j]==i)//如果陣列裡有相符的數count就加一
        			count++;
        	}
        	if(count>1)//判斷1數是否超過1個以上
        		System.out.print("數字"+i+"重複"+count+"次");
        }
    }
}
