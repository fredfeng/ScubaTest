import framework.scuba.helper.AliasHelper;

 
public class A { 

   Object f;
   Object g;
   Object h;
   
   A j;
   
   A(Object o1, Object o2, Object o3) {
     this.f = o1;
     this.g = o2;
     this.h = o3;
   }
   
	void m() {
		this.j = this;
	}
   
   public Object foo() {
     return f;
   }
   
	Object id(Object x) {
		return x;
	}

   public static void main(String[] args) { 
     test1();
     
     
   }
   
	public static void test1() {
		// Original test case from Isil showing the precision by constraint.
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();

		A a = new A(o1, o2, o3);
		B b = new B(o1, o2, o3);
		C c = new C(o1, o2, o3);

		Object o4 = test11(a);
		Object o5 = test11(b);
		Object o6 = test11(c);

		AliasHelper.notAlias(o4, o5); // false
		AliasHelper.notAlias(o4, o6); // false
		AliasHelper.notAlias(o5, o6); // false

		// Showing array allocated in different obj.
		Bldg bldg = new Bldg();
		AliasHelper.notAlias(bldg.events.elems, bldg.floors.elems);// false

		// test case to show that kcfa is better than kobj from Manu
		Object x = new Object(); // o1
		Object y = new Object(); // o2
		Object z = a.id(x); // z -> o1
		Object w = a.id(y); // w -> o2
		AliasHelper.notAlias(z, w); // false

		// test case to show that kobj is better than kcfa
		A a2 = new A(o1, o2, o3); // a1
		boolean p = true;
		A a3 = p ? a : a2; // a3 -> {a, a2}
		a3.m(); // a.j -> a, a2.j -> a2
		AliasHelper.notAlias(a.j, a2);
		AliasHelper.notAlias(a2.j, a);
		AliasHelper.alias(a.j, a);
	}
   
   public static Object test11(A a) {
      return a.foo();
   }
}