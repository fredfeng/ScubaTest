package scuba.test.testcase.intra;

import framework.scuba.helper.AliasHelper;

public class Test {

	public static void test() {
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
		test8();
		test9();
		test10();
	}

	// testing new and assignment
	public static void test1() {
		A t1 = new A();
		A t2 = new A();
		AliasHelper.notAlias(t1, t2);

		A t3 = t2;
		AliasHelper.alias(t2, t3);
		AliasHelper.notAlias(t1, t3);
	}

	// test load and store
	public static void test2() {
		B t1 = new B();
		t1.f = new C();
		B t2 = new B();
		t2.f = new C();
		AliasHelper.notAlias(t1.f, t2.f);

		C t3 = t1.f;
		C t4 = t1.f;
		C t5 = t2.f;
		AliasHelper.alias(t3, t4);
		AliasHelper.notAlias(t4, t5);
		AliasHelper.notAlias(t3, t5);
	}

	// test new array and assignment
	public static void test3() {
		A[] t1 = new A[10];
		A[] t2 = new A[10];
		AliasHelper.notAlias(t1, t2);

		A[] t3 = t2;
		AliasHelper.notAlias(t1, t3);
		AliasHelper.alias(t2, t3);
	}

	// test multi new and assignment
	public static void test4() {
		A[][] t1 = new A[10][10];
		A[][] t2 = new A[10][10];
		AliasHelper.notAlias(t1, t2);

		// we cannot test because of empty point-to sets of Chord
		A[] t3 = t1[0];
		A[] t4 = t2[0];
		// AliasHelper.notAlias(t3, t4);
		// AliasHelper.notAlias(t1, t3);
		// AliasHelper.notAlias(t1, t4);
		// AliasHelper.notAlias(t2, t3);
		// AliasHelper.notAlias(t2, t4);

		A[][] t5 = t2;
		AliasHelper.alias(t2, t5);
		AliasHelper.notAlias(t1, t5);

		t3[0] = new A();
		t4[0] = new A();
		// AliasHelper.notAlias(t3[0], t4[0]);
	}

	// test array load and store
	public static void test5() {
		A[] t1 = new A[10];
		A t2 = new A();
		A t3 = new A();
		t1[0] = t2;
		t1[1] = t3;
		AliasHelper.notAlias(t2, t3);
		AliasHelper.alias(t1[0], t1[1]);

		A t4 = t1[0];
		A t5 = t1[1];
		AliasHelper.alias(t4, t5);
	}

	public static void test6() {

	}

	public static void test7() {

	}

	public static void test8() {

	}

	public static void test9() {

	}

	public static void test10() {

	}

}
