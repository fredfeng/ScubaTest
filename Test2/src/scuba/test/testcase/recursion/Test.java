package scuba.test.testcase.recursion;

public class Test {

	public static void test() {
		test1();
	}
	
	private static void test1() {
		//a simplified version of regular expression.
		new Pattern("hi");
	}
}