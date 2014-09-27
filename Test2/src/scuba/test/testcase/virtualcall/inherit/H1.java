package scuba.test.testcase.virtualcall.inherit;

public class H1 implements I1 {

	public static final Object sf = new Object();

	public Object f, g, h;

	public H1() {

	}

	public H1(Object o1, Object o2, Object o3) {
		f = o1;
		g = o2;
		h = o3;
	}

	public Object foo() {
		but();
		return goo();
	}

	public Object goo() {
		return f;
	}

	public void but() {
		f = g;
	}

	public Object retG() {
		return sf;
	}
}
