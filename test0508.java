import java.util.Scanner;
interface A{
	int a = 1;
	int A();
}
interface D extends A{
	int a = 2;
}
class C implements A,D{
	int a = 3;
	public int A(){
		return 5;
	}
}
//--------------------
abstract class B{
	B(){
	}
	int b = 1;
	abstract int B();
	void B(int a){
	}
}
class B1 extends B{
	int B(){
		return 7;	
	}
}
public class test0508 {
    public static void main(String[] args) {
    	C test = new C();
    	System.out.println(test.a);
    	System.out.println(test.A());
    	B1 d = new B1();
    	System.out.println(d.B());
    }
}
