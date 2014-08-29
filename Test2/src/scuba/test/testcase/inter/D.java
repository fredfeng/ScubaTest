package scuba.test.testcase.inter;

public class D {

	protected static B f = new B();

	protected B g;

	public D() {
		this.g = f;
	}

}
