package scuba.test.testcase.ctxt;

public class Test {

	public static void test() {
		test1();
		test2();
	}

	public static void test1() {
		System.out.println("hello");
		test1();
		A t1 = new A();
	}

	public static void test2() {
		C t1 = new C();
		D t2 = t1.f;
	}

}
