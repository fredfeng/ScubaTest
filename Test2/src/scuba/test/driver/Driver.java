package scuba.test.driver;

public class Driver {

	public static void main(String[] args) {
		// testing randomly
		scuba.test.testcase.virtualcall.Test.test();
		// testing conjunction and disjunction of cst and container
		scuba.test.testcase.virtualcall.Test.test();
		// test intra-proc
		scuba.test.testcase.intra.Test.test();
	}

}
