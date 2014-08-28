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
		test11();
		test12();
		test13();
		test14();
		test15();
		test16();
		test17();
	}

	// test new and assignment
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

		// we cannot test followings because of empty point-to sets of Chord
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

	// test condition
	public static void test6() {
		A t1 = new A();
		A t2 = new A();
		A t3 = null;
		A t4 = null;
		int i = 0;
		if (i < 10) {
			t3 = t1;
			t4 = t2;
		} else {
			t3 = t2;
			t4 = t1;
		}
		AliasHelper.alias(t3, t4);
		AliasHelper.notAlias(t1, t2);
		AliasHelper.alias(t1, t3);
		AliasHelper.alias(t1, t4);
		AliasHelper.alias(t2, t3);
		AliasHelper.alias(t2, t4);
	}

	// test loop
	public static void test7() {
		A t1 = new A();
		A t2 = null;
		A t3 = null;
		A t5 = t1;
		for (int i = 0; i < 10; i++) {
			t2 = t1;
			t1 = new A();
			t3 = t1;
		}
		AliasHelper.alias(t2, t3);
		AliasHelper.alias(t1, t2);
		AliasHelper.alias(t1, t3);
		AliasHelper.notAlias(t3, t5);
	}

	// test regular recursive field
	public static void test8() {
		D t1 = new D();
		t1.j = new D();
		t1.j.j = new D();
		AliasHelper.notAlias(t1, t1.j);
		AliasHelper.notAlias(t1, t1.j.j);
		AliasHelper.notAlias(t1.j, t1.j.j);
		// do transitive closure
		D t2 = test8Helper1(t1);
		AliasHelper.notAlias(t2, t1);
		AliasHelper.alias(t2, t1.j);
		AliasHelper.alias(t2, t1.j.j);
		// no transitive closure
		D t3 = test8Helper2(t1);
		AliasHelper.notAlias(t3, t1);
		AliasHelper.alias(t3, t1.j);
		AliasHelper.notAlias(t3, t1.j.j);
	}

	public static D test8Helper1(D a1) {
		return a1.j.j;
	}

	public static D test8Helper2(D a1) {
		return a1.j;
	}

	// test regular recursive field
	public static void test9() {
		D t1 = new D();
		t1.j = new D();
		t1.g = new D();
		t1.j.j = new D();
		t1.j.g = new D();
		t1.g.j = new D();
		t1.g.g = new D();
		D t2 = test8Helper1(t1);
		AliasHelper.notAlias(t2, t1);
		AliasHelper.notAlias(t2, t1.g);
		AliasHelper.notAlias(t2, t1.g.j);
		AliasHelper.notAlias(t2, t1.g.g);
		AliasHelper.alias(t2, t1.j);
		AliasHelper.alias(t2, t1.j.j);
		AliasHelper.notAlias(t2, t1.j.g);
	}

	// test regular recursive field
	public static void test10() {
		D t1 = new D();
		t1.j = new D();
		t1.j.j = new D();
		t1.j.j.j = new D();
		D t2 = test10Helper1(t1);
		AliasHelper.alias(t2, t1);
		AliasHelper.alias(t2, t1.j);
		AliasHelper.alias(t2, t1.j.j);
		AliasHelper.alias(t2, t1.j.j.j);
	}

	public static D test10Helper1(D a1) {
		D t1 = a1;
		int i = 0;
		while (true) {
			if (i >= 3) {
				return t1;
			} else {
				t1 = t1.j;
				i++;
			}
		}
	}

	// test regular recursive field
	public static void test11() {
		E t1 = new E();
		t1.h = new F();
		t1.h.k = new E();
		t1.h.k.h = new F();
		t1.h.k.h.k = new E();
		F t2 = test11Helper1(t1);
		AliasHelper.notAlias(t2, t1);
		AliasHelper.alias(t2, t1.h);
		AliasHelper.notAlias(t2, t1.h.k);
		AliasHelper.alias(t2, t1.h.k.h);
		AliasHelper.notAlias(t2, t1.h.k.h.k);
	}

	public static F test11Helper1(E a1) {
		return a1.h.k.h;
	}

	// test regular recursive field
	public static void test12() {
		E t1 = new E();
		t1.h = new F();
		t1.h.k = t1;
		F t2 = test12Helper1(t1);
		AliasHelper.alias(t2, t1.h);
		AliasHelper.notAlias(t2, t1);
		AliasHelper.notAlias(t2, t1.h.k);
		AliasHelper.alias(t1, t1.h.k);

		E t3 = test12Helper2(t1);
		AliasHelper.alias(t3, t1);
		AliasHelper.notAlias(t3, t1.h);
		AliasHelper.alias(t3, t1.h.k);
	}

	public static F test12Helper1(E a1) {
		return a1.h.k.h;
	}

	public static E test12Helper2(E a1) {
		return a1.h.k.h.k;
	}

	// test regular recursive field
	public static void test13() {
		E t1 = new E();
		t1.h = new F();
		t1.h.k = new E();
		t1.h.k.h = new F();
		t1.h.k.h.k = t1;

		E t2 = test13Helper1(t1);
		AliasHelper.notAlias(t2, t1);
		AliasHelper.notAlias(t2, t1.h);
		AliasHelper.alias(t2, t1.h.k);
		AliasHelper.notAlias(t2, t1.h.k.h);
		AliasHelper.notAlias(t2, t1.h.k.h.k);

		E t3 = test13Helper2(t1);
		AliasHelper.alias(t3, t1);
		AliasHelper.notAlias(t3, t1.h);
		AliasHelper.alias(t3, t1.h.k);
		AliasHelper.notAlias(t3, t1.h.k.h);
		AliasHelper.alias(t3, t1.h.k.h.k);

		F t4 = test13Helper3(t1);
		AliasHelper.notAlias(t4, t1);
		AliasHelper.alias(t4, t1.h);
		AliasHelper.notAlias(t4, t1.h.k);
		AliasHelper.notAlias(t4, t1.h.k.h);
		AliasHelper.notAlias(t4, t1.h.k.h.k);

		F t5 = test13Helper4(t1);
		AliasHelper.notAlias(t5, t1);
		AliasHelper.alias(t5, t1.h);
		AliasHelper.notAlias(t5, t1.h.k);
		AliasHelper.alias(t5, t1.h.k.h);
		AliasHelper.notAlias(t5, t1.h.k.h.k);
	}

	public static E test13Helper1(E a1) {
		return a1.h.k;
	}

	public static E test13Helper2(E a1) {
		return a1.h.k.h.k;
	}

	public static F test13Helper3(E a1) {
		return a1.h;
	}

	public static F test13Helper4(E a1) {
		return a1.h.k.h;
	}

	// test regular recursive field
	public static void test14() {
		E t1 = new E();
		t1.h = new F();
		t1.h.k = new E();
		t1.h.k.h = t1.h;
		t1.h.k.l = new F();
		t1.h.k.l.k = t1;
		t1.l = new F();
		t1.l.k = new E();
		t1.l.k.h = t1.h.k.l;
		t1.l.k.l = new F();
		t1.l.k.l.k = t1.l.k;

		F t2 = test14Helper1(t1);
		F t3 = test14Helper2(t1);
		F t4 = test14Helper3(t1);
		F t5 = test14Helper4(t1);
		F t6 = test14Helper5(t1);
		F t7 = test14Helper6(t1);
		E t8 = test14Helper7(t1);
		E t9 = test14Helper8(t1);
		E t10 = test14Helper9(t1);
		E t11 = test14Helper10(t1);
		E t12 = test14Helper11(t1);
		E t13 = test14Helper12(t1);
		E t14 = test14Helper13(t1);
	}

	public static F test14Helper1(E a1) {
		return a1.h;
	}

	public static F test14Helper2(E a1) {
		return a1.h.k.h;
	}

	public static F test14Helper3(E a1) {
		return a1.h.k.l.k.h;
	}

	public static F test14Helper4(E a1) {
		return a1.l;
	}

	public static F test14Helper5(E a1) {
		return a1.l.k.l;
	}

	public static F test14Helper6(E a1) {
		return a1.l.k.h.k.l;
	}

	public static E test14Helper7(E a1) {
		return a1.h.k;
	}

	public static E test14Helper8(E a1) {
		return a1.h.k.h.k;
	}

	public static E test14Helper9(E a1) {
		return a1.h.k.l.k;
	}

	public static E test14Helper10(E a1) {
		return a1.h.k.h.k.l.k;
	}

	public static E test14Helper11(E a1) {
		return a1.l.k;
	}

	public static E test14Helper12(E a1) {
		return a1.l.k.l.k;
	}

	public static E test14Helper13(E a1) {
		return a1.l.k.l.k.h.k;
	}

	public static void test15() {

	}

	public static void test16() {

	}

	public static void test17() {

	}

}
