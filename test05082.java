import java.util.Scanner;
interface comp{
	String Apple = "Apple";
	String Banana = "Banana";
}
class cargo implements comp{
	void comp1(){
		System.out.println("cargo �ӫ~ 1");
	}
	void comp2(){
		System.out.println("cargo �ӫ~ 2");
	}
}
class A extends cargo{
	void A(int a){
		System.out.println("�ӱ��� A �Ͳ� cargo �ӫ~ 1");
	}
	void A(int a,int a1,int a2){
		System.out.println("�ӱ��� A �ӫ~");
	}
}
class B extends cargo{
	void B(int b){
		System.out.println("�ӱ��� B �Ͳ� cargo �ӫ~ 2");
	}
	void B(int b,int b1,int b2){
		System.out.println("�ӱ��� B �ӫ~");
	}
}
class C extends cargo{
	void C(int c){
		System.out.println("�ӱ��� C �Ͳ� cargo �ӫ~ 1");
	}
	void C(int c,int c1){
		System.out.println("�ӱ��� C �Ͳ� cargo �ӫ~ 2");
	}
	void C(int c,int c1,int c2){
		System.out.println("�ӱ��� C �ӫ~");
	}
}
public class test05082 {
    public static void main(String[] args) {
    	A banana1 = new A();
    	System.out.println("1.\n�ʶR�H�G" + banana1.Banana);
    	banana1.A(1);
    	banana1.comp2();
    	banana1.A(1,1,1);
    	B apple1 = new B();
    	System.out.println("\n2.\n�ʶR�H�G" + apple1.Apple);
    	apple1.comp1();
    	apple1.B(2);
    	apple1.B(2,2,2);
    	C apple2 = new C();
    	System.out.println("\n3.\n�ʶR�H�G" + apple2.Apple);
    	apple2.C(3);
    	apple2.C(3,3);
    	apple2.C(3,3,3);
    	
	}
}