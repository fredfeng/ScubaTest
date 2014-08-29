package scuba.test.testcase.inter;

public class E {

	protected B f;

	protected B g;

	public E() {
		this.f = new B();
		this.g = new B();
	}

	public B getF() {
		return this.f;
	}

	public B getG() {
		return this.g;
	}

	public B getFG() {
		int i = 0;
		if (i < 10) {
			return this.f;
		} else {
			return this.g;
		}
	}

}