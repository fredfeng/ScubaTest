 
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
       
       check_alias(o4, o5); //false
       check_alias(o4, o6); //false
       check_alias(o5, o6); //false

   }
   
   public static Object test11(A a) {
      return a.foo();
   }
   
   public static void check_alias(Object x, Object y){
      if(x == y) System.out.println("Alias");
      else System.out.println("Don't alias");
   }
   
   
   
   
   
}