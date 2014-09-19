package scuba.test.testcase.ctxt;

public class Test {

	public static void test() {
		test1();
	}

	public static void test1() {
		System.out.println("hel");
		test1();
		A t = new A();
	}

}
