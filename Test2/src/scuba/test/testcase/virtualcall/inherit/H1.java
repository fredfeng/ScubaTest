package scuba.test.testcase.virtualcall.inherit;

public class H1 {
	
	Object f, g, h;
	
	public H1(Object o1, Object o2, Object o3) {
		f = o1;
		g = o2;
		h = o3;
	}
	
	public Object foo() {
		return goo();
	}
	
	public Object goo() {
		return f;
	}
}
