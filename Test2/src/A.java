import framework.scuba.helper.AliasHelper;

 
public class A { 

   Object f;
   Object g;
   Object h;
   
   A(Object o1, Object o2, Object o3) {
     this.f = o1;
     this.g = o2;
     this.h = o3;
   }
   
   public Object foo() {
     return f;
   }
 
   public static void main(String[] args) { 
     test1();
     
     
   }
   
   public static void test1() {
       Object o1 = new Object();
       Object o2 = new Object();
       Object o3 = new Object();
       
       A a = new A(o1, o2, o3);
       B b = new B(o1, o2, o3);
       C c = new C(o1, o2, o3);
       
       Object o4 = test11(a);
       Object o5 = test11(b);
       Object o6 = test11(c);
       
       AliasHelper.notAlias(o4, o5); //false
       AliasHelper.notAlias(o4, o6); //false
       AliasHelper.notAlias(o5, o6); //false

   }
   
   public static Object test11(A a) {
      return a.foo();
   }
}