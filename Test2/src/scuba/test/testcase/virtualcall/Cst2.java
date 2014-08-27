package scuba.test.testcase.virtualcall;

public class Cst2 extends Cst1 {
	
	public Cst2(SubCst1 o1, SubCst1 o2) {
		super(o1, o2);
	}

	public Object bar() {
		return h.goo();
	}
}
