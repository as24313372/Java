public class test11214{
	/*
	 *	1 2 3
	 *
	 *	4 5 6
	 *
	 *	7 8 9
	 *
	 */
    public static void main(String[] args) {
        for(int i=1;i<10;i+=3){//判斷列i=>1,4,7
        	for(int j=1;j<10;j++){//1-9
        		System.out.printf("%d*%d=%2d ",i,j,i*j);		//i	 =1,4,7
        		System.out.printf("%d*%d=%2d ",i+1,j,(i+1)*j);	//i+1=2,5,8
        		System.out.printf("%d*%d=%2d\n",i+2,j,(i+2)*j); //i+2=3,6,9
        	}
        	System.out.print("\n");//跑完一行之後要空一行
        }
    }
}
