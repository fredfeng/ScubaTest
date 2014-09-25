package scuba.test.testcase.recursion;

public class B {

	B next = new B();

	public void foo(A a) {
		A t1 = new A();
		next.foo(t1);
	}

}
