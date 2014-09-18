package scuba.test.testcase.designpattern;

import framework.scuba.helper.AliasHelper;

public class Test {

	public static void test() {
		test1();
		test2();
	}

	public static void test1() {
		// test singleton.
		// all vars should be alias.
		Object o1 = ClassicSingleton.getInstance();
		Object o2 = ClassicSingleton.getInstance();
		AliasHelper.alias(o1, o2);
	}

	public static void test2() {
		// test factory pattern.
		// all vars should not be alias.
		Object o1 = DogFactory.getDog("small");
		Object o2 = DogFactory.getDog("big");
		AliasHelper.notAlias(o1, o2);
		Object o3 = DogFactory.getDog("small");
		AliasHelper.notAlias(o1, o3);
	}

}
