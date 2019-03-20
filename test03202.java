class Element{
	int size;
	String color;
	public Element(int size, String color){
		this.size = size;
		this.color = color;
	}
	public Element(int size){
		this.size = size;
		this.color = "red";
	}
	public Element(String color){
		this.size = 10;
		this.color = color;
	}
	public String toString(){
		return "size = " + this.size + ", color = " + this.color;
	}
}
public class test03202 {
    public static void main(String[] args) {
		Element e = new Element(30,"yellow");
		System.out.print(e);
		e = new Element(20);
		System.out.print(e);
		e = new Element("blue");
		System.out.print(e);
		e.toString();
		System.out.print(e);
    }
}

