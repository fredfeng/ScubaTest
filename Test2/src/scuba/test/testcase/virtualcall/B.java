package scuba.test.testcase.virtualcall;
 
public class B extends A {

	public B(Object o1, Object o2, Object o3) {
		super(o1, o2, o3);
	}

	public Object foo() {
		return this.g;
	}
	
//	public void buu() {
//		return k.bar(x);
//	}
//
//	public A bar(A x) {
//		return x.goo();
//	}

	public A goo() {
		return l;
	}

}