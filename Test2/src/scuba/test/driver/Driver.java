package scuba.test.driver;

public class Driver {

	public static void main(String[] args) {
		// testing randomly
		scuba.test.testcase.virtualcall.Test.test();
		// testing conjunction and disjunction of cst and container
		scuba.test.testcase.virtualcall.Test.test();
		// testing container
		scuba.test.testcase.container.Test.testContainer();
		// test intra-proc
		scuba.test.testcase.intra.Test.test();
	}

}
