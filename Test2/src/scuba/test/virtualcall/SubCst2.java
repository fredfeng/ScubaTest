package scuba.test.virtualcall;

public class SubCst2 extends SubCst1 {
	public SubCst2(Object o1, Object o2) {
		super(o1, o2);
	}

	public Object goo() {
		return l;
	}
}
