package scuba.test.testcase.semantics;

public class Test {

	public static void main(String[] args) {
		test();
	}

	public static void test() {
		test1();
	}

	public static void test1() {
		A t1 = new A("hello");
		System.out.println("t1 before : " + t1);
		test1Helper1(t1);
		System.out.println("t1 after : " + t1);
	}

	public static void test1Helper1(A a) {
		// a.modifyStr("world");
		// a = new A("world");
		A t2 = a;
		System.out.println("t2 : " + t2);
	}

}
