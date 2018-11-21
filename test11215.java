public class test11215 {
	/* 
	 *
	 */
    public static void main(String[] args) {
        System.out.println("¥ÀÂû"+f1(20));
        System.out.println("¤½Âû"+m1(20));
    }
    public static int f1(int n){
    	if(n==0)return 1;
    	return m1(n)+m1(n-1);
    }
    public static int m1(int n){
    	if(n==0)return 0;
    	return f1(n-1);
    }
}
