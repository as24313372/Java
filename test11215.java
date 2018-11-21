public class test11215 {
	/* 1隻母蟻是由1隻母蟻跟一隻公蟻所生
	 * 一隻公蟻是由1隻母蟻所生
	 *而母蟻的上20代為
	 *母蟻=1,1,2,3,5,8,13,21.....
	 *公蟻=0,1,1,2,3,5, 8,13.....
	 *代數=0,1,2,3,4,5, 6, 7.....
	 *
	 */
    public static void main(String[] args) {
        System.out.println("母蟻"+f1(20));
        System.out.println("公蟻"+m1(20));
    }
    //由上面規律得出上n代的母蟻是上n代的公蟻跟上(n-1)代的公蟻相加
    //如果是第0代則是1隻母蟻
    public static int f1(int n){
    	if(n==0)return 1;
    	return m1(n)+m1(n-1);
    }
    //利用遞迴來運算
    //由上面規律得出上n代的公蟻是上n-1代的母蟻
    //第0代是0隻公蟻
    public static int m1(int n){
    	if(n==0)return 0;
    	return f1(n-1);
    }
}
