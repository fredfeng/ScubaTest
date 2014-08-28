package scuba.test.testcase.virtualcall.inherit;

public class H2 extends H1 {
	
	public H2(Object o1, Object o2, Object o3) {
		super(o1, o2, o3);
	}
	
	public Object goo() {
		return g;
	}

	@Override
	public void but() {
		g = h;
	}

}
