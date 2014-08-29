package scuba.test.testcase.inter;

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
		test11();
		test12();
		test13();
		test14();
		test15();
		test16();
		test17();
		test18();
		test19();
		test20();
		test21();
		test22();
		test23();
		test24();
	}

	// test constructor
	public static void test1() {
		A1 t1 = new A1();
		A1 t2 = new A1();
		AliasHelper.notAlias(t1, t2);
		AliasHelper.notAlias(t1.f, t2.f);
	}

	// test constructor
	public static void test2() {
		B t1 = new B();
		A1 t2 = new A1(t1);
		A1 t3 = new A1(t1);
		AliasHelper.notAlias(t2, t3);
		AliasHelper.alias(t2.f, t3.f);
		AliasHelper.alias(t2.f, t1);
		AliasHelper.alias(t3.f, t1);
	}

	// test constructor with inheritance
	public static void test3() {
		A2 t1 = new A2();
		A2 t2 = new A2();
		AliasHelper.notAlias(t1, t2);
		AliasHelper.notAlias(t1.f, t2.f);
	}

	// test constructor with inheritance
	public static void test4() {
		B t1 = new B();
		A2 t2 = new A2(t1);
		A2 t3 = new A2(t1);
		AliasHelper.notAlias(t2, t3);
		AliasHelper.alias(t2.f, t3.f);
	}

	// test constructor
	public static void test5() {
		C t1 = new C();
		C t2 = new C();
		AliasHelper.notAlias(t1.f, t1.g);
		AliasHelper.notAlias(t1, t2);
		AliasHelper.notAlias(t1.f, t2.f);
		AliasHelper.notAlias(t1.g, t2.g);
	}

	// test constructor
	public static void test6() {
		C t1 = new C(1);
		C t2 = new C(1);
		AliasHelper.alias(t1.f, t1.g);
		AliasHelper.notAlias(t1, t2);
		AliasHelper.alias(t2.f, t2.g);
		AliasHelper.notAlias(t1.f, t2.f);
		AliasHelper.notAlias(t1.g, t2.g);
	}

	// test constructor
	public static void test7() {
		B t1 = new B();
		C t2 = new C(t1, t1);
		AliasHelper.alias(t2.f, t2.g);
	}

	// test constructor with a global field
	public static void test8() {
		D t1 = new D();
		D t2 = new D();
		B t3 = D.f;
		AliasHelper.alias(t1.g, t2.g);
		AliasHelper.alias(t1.g, t3);
		AliasHelper.alias(t2.g, t3);
		AliasHelper.notAlias(t1, t2);
	}

	// test return value
	public static void test9() {
		E t1 = new E();
		B t2 = t1.getF();
		AliasHelper.alias(t1.f, t2);
	}

	// test return value
	public static void test10() {
		E t1 = new E();
		B t2 = t1.getF();
		B t3 = t1.getG();
		B t4 = t1.getFG();
		AliasHelper.notAlias(t2, t3);
		AliasHelper.alias(t2, t4);
		AliasHelper.alias(t3, t4);
	}

	// test context for allocations
	public static void test11() {
		F t1 = new F();
		B t2 = t1.alloc();
		B t3 = t1.alloc();
		AliasHelper.notAlias(t2, t3);
	}

	public static void test12() {

	}

	public static void test13() {

	}

	public static void test14() {

	}

	public static void test15() {

	}

	public static void test16() {

	}

	public static void test17() {

	}

	public static void test18() {

	}

	public static void test19() {

	}

	public static void test20() {

	}

	public static void test21() {

	}

	public static void test22() {

	}

	public static void test23() {

	}

	public static void test24() {

	}
}
