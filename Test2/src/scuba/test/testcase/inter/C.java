package scuba.test.testcase.inter;

public class C {

	protected B f;

	protected B g;

	public C() {
		this.f = new B();
		this.g = new B();
	}

	public C(int a1) {
		this.f = new B();
		this.g = this.f;
	}

	public C(B f, B g) {
		this.f = f;
		this.g = g;
	}

}
