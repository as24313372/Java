class A{
	static{
		System.out.println("111");
	}
	A(){
		System.out.println("222");
	}
}
class B extends A{
	static{
		System.out.println("333");
	}
	B(){
		System.out.println("444");
	}
}

public class test03203 {
    public static void main(String[] args) {
        B a1 = new B();
        A b1 = new A();
    }
}
/*111
 *222
 *333
 *222
 *444
 *---
 *111
 *333
 *222
 *444
 *222
 *---
 *error
 */
