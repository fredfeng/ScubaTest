package scuba.test.virtualcall;

public class SubCst1 {
	public Object l, m;

	public SubCst1(Object o1, Object o2) {
		l = o1;
		m = o2;
	}

	public Object goo() {
		return m;
	}
}
