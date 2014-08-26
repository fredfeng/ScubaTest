import framework.scuba.helper.AliasHelper;

public class A {

	Object f;
	Object g;

	public static void main(String[] args) {
		// test1();
		test2();
	}

	public static void test1() {
		Object o1 = new Object();
		Object o2 = new Object();
		A v1 = new A(o1, o2);
		A v2 = new B(o1, o2);
		A v3 = new C(o1, o2);
		A v4 = new D(o1, o2);
		A v5 = new E(o1, o2);
		Object x = v1.bar();
		Object y = v2.bar();
		Object z = v3.bar();
		Object w = v4.bar();
		Object zz = v5.bar();

		AliasHelper.alias(x, y); // true
		AliasHelper.notAlias(x, z); // false
		AliasHelper.notAlias(y, z); // false
		AliasHelper.alias(x, w); // true;
		AliasHelper.notAlias(x, zz); // false
		AliasHelper.notAlias(z, zz); // false

		System.out.println("-----TEST1 DONE------");
	}

	public static void test2() {
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();
		A v1 = new A(o1, o2);
		A v2 = new B(o1, o2, o3);
		A v3 = new C(o1, o2, o3);
		A v4 = new D(o1, o2, o3);
		A v5 = new E(o1, o2, o3);
		test2_helper(v1, v2, v3, v4, v5);
	}

	public static void test2_helper(A v1, A v2, A v3, A v4, A v5) {
		Object a = v1.goo(); // o1
		Object b = v2.goo(); // o3
		Object c = v3.goo(); // o3
		Object d = v4.goo(); // new object
		Object e = v5.goo(); // o1

		AliasHelper.notAlias(a, b); // false
		AliasHelper.notAlias(a, c); // false
		AliasHelper.notAlias(a, d); // false
		AliasHelper.alias(a, e); // true
		AliasHelper.alias(b, c); // true
		AliasHelper.notAlias(b, d); // false
		AliasHelper.notAlias(b, e); // false
		AliasHelper.notAlias(c, d); // false
		AliasHelper.notAlias(c, e); // false
		AliasHelper.notAlias(d, e); // false

		System.out.println("-----TEST2 PART 1 DONE------");

		Object f = v3.goo2();
		Object g = v5.goo2();
		Object h = v3.goo3();
		AliasHelper.notAlias(a, f); // false
		AliasHelper.alias(a, g); // true
		AliasHelper.notAlias(a, h); // false
		AliasHelper.alias(b, f); // true

	}

	// --------------------------------------------

	A(Object a, Object b) {
		this.f = a;
		this.g = b;
	}

	Object bar() {
		return foo();
	}

	Object foo() {
		return this.f;
	}

	Object goo() {
		return foo();
	}

	Object goo2() {
		return this.f;
	}

	Object goo3() {
		return goo2();
	}

}