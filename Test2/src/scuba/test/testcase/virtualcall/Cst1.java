package scuba.test.testcase.virtualcall;

public class Cst1 {
	SubCst1 g, h, i;

	public Cst1(SubCst1 o1, SubCst1 o2) {
		g = o1;
		h = o2;
	}
	
	public Object gut() {
		return bar();
	}
	
	public Object bar() {
		return g.goo();
	}
}
