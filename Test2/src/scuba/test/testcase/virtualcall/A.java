package scuba.test.testcase.virtualcall;

public class A {

	public Object f;
	public Object g;
	public Object h;

	public A j,k,l,m;

	public A(Object o1, Object o2, Object o3) {
		this.f = o1;
		this.g = o2;
		this.h = o3;
	}

	public void m() {
		this.j = this;
	}

	public Object foo() {
		return f;
	}

	public Object id(Object x) {
		return x;
	}
	
//	public Object buu() {
//		return k.bar(x);
//	}

	public Object bar(A x) {
		return x.goo();
	}

	public Object goo() {
		return l;
	}
}