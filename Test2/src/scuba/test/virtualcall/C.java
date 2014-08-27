package scuba.test.virtualcall;

public class C extends B {

	public C(Object o1, Object o2, Object o3) {
		super(o1, o2, o3);
	}

	public Object foo() {
		return h;
	}

}