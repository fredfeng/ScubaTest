package scuba.test.testcase.ctxt;

public class C {

	protected D f;

	public C() {
		this.f = new D();
		foo();
	}

	public void foo() {
		new C();
	}
}
