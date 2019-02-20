class test{
	test(int a){
		sop(a + "");
	}
	test(){
		sop("456");
	}
	void test(){
		sop("789");
	}
	void test(int a){
		sop(a + "");
	}
	static void sop(String s){
    	System.out.println(s);
	}
}

public class test02202 {
    public static void main(String[] args) {
        test lee = new test(123);
        lee.test();
        lee.test(1234);
        lee = new test();
    }
}