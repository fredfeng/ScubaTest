package scuba.test.driver;

public class Driver {

	public static void main(String[] args) {
		// testing randomly
		scuba.test.testcase.virtualcall.Test.test();
		// testing conjunction and disjunction of cst and container
		scuba.test.testcase.virtualcall.Test.test();
		// testing container
		scuba.test.testcase.container.Test.testContainer();
		// testing design patterns
		scuba.test.testcase.designpattern.Test.test();
		// tricky test cases.
		// scuba.test.testcase.tricky.Test.test();
		// test intra-proc
		scuba.test.testcase.intra.Test.test();
		// test inter-proc
		scuba.test.testcase.inter.Test.test();
		// testing recusions.
		// No alias assertion in this test suite, but our analysis will
		// not terminate within 10 min if you set alloc length > 1.
		// run this test case when you are ready for it.
		// scuba.test.testcase.recursion.Test.test();
	}

}
