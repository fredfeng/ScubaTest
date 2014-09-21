package scuba.test.testcase.ctxt;

public class C {

	protected D f;

	public static void main(String[] args) {
		new C();
	}

	public C() {
		this.f = new D();
		foo();
	}

	public void foo() {
		new C();
	}
}
