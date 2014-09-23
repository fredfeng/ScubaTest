package scuba.test.testcase.semantics;

public class A {

	String str;

	public A() {

	}

	public A(String str) {
		this.str = str;
	}

	public void modifyStr(String str) {
		this.str = str;
	}

	public String toString() {
		return str;
	}
}
