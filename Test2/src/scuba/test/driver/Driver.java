package scuba.test.driver;

public class Driver {

	public static void main(String[] args) {
		// testing randomly
		scuba.test.testcase.virtualcall.Test.test();
		// testing conjunction and disjunction of cst.
		scuba.test.testcase.virtualcall.Test.testCst();
		// test container.
		scuba.test.testcase.container.Test.testContainer();
	}

}
