import java.util.Scanner;
interface comp{
	String Apple = "Apple";
	String Banana = "Banana";
}
class cargo implements comp{
	void comp1(){
		System.out.println("cargo 商品 1");
	}
	void comp2(){
		System.out.println("cargo 商品 2");
	}
}
class A extends cargo{
	void A(int a){
		System.out.println("承接商 A 生產 cargo 商品 1");
	}
	void A(int a,int a1,int a2){
		System.out.println("承接商 A 商品");
	}
}
class B extends cargo{
	void B(int b){
		System.out.println("承接商 B 生產 cargo 商品 2");
	}
	void B(int b,int b1,int b2){
		System.out.println("承接商 B 商品");
	}
}
class C extends cargo{
	void C(int c){
		System.out.println("承接商 C 生產 cargo 商品 1");
	}
	void C(int c,int c1){
		System.out.println("承接商 C 生產 cargo 商品 2");
	}
	void C(int c,int c1,int c2){
		System.out.println("承接商 C 商品");
	}
}
public class test05082 {
    public static void main(String[] args) {
    	A banana1 = new A();
    	System.out.println("1.\n購買人：" + banana1.Banana);
    	banana1.A(1);
    	banana1.comp2();
    	banana1.A(1,1,1);
    	B apple1 = new B();
    	System.out.println("\n2.\n購買人：" + apple1.Apple);
    	apple1.comp1();
    	apple1.B(2);
    	apple1.B(2,2,2);
    	C apple2 = new C();
    	System.out.println("\n3.\n購買人：" + apple2.Apple);
    	apple2.C(3);
    	apple2.C(3,3);
    	apple2.C(3,3,3);
    	
	}
}