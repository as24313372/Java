class A{
	static{
		System.out.print("Chin-Yi ");
	}
	int n=1;
	A(){
		System.out.print("NO.");
	}
	A(int n){
		this();
		this.n = n;
		System.out.print(n + " ");
	}
	void A(int n){
		System.out.println("111");
	}
	void A(int...n){
		System.out.println("222");
	}
}
class B extends A{
	static{
		System.out.print("CSIE ");
	}
	int n = 2000;
	B(){
		super(1);
		System.out.print("YA! ");
	}
	B(int n){
		this();
		System.out.println(this.n + "-" + n);
	}
	void A(){
		System.out.println("333");
	}
	void A(int...n){
		System.out.println("444");
	}
}
public class test03201 {
    public static void main(String[] args) {
        A a1 = new A(1);
        A a2 = new A(2);
        B b1 = new B(2018);
        B b2 = new B(2020);
        a1.A();
        a2.A(10);
        b1.A();
        b1.A(10,20);
    }
}
