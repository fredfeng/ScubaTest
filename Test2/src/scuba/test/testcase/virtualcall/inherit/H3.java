package scuba.test.testcase.virtualcall.inherit;

public class H3 extends H1{

	public H3(Object o1, Object o2, Object o3) {
		super(o1, o2, o3);
	}
	
	public Object goo() {
		return h;
	}
	
	@Override
	public void but() {
		f = h;
	}
}
