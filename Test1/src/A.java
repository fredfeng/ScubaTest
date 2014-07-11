 
public class A { 

   Object f;
   Object g;
 
   public static void main(String[] args) { 
     test1();
     test2();
     
     
   }
   
   public static void test1() {
     Object o1 = new Object();
     Object o2 = new Object();
     A v1 = new A(o1, o2);
     A v2 = new B(o1, o2);
     A v3 = new C(o1, o2);
     A v4 = new D(o1, o2);
     A v5 = new E(o1, o2);
     Object x = v1.bar();
     Object y = v2.bar();
     Object z = v3.bar();
     Object w = v4.bar();
     Object zz = v5.bar();
     
     check_alias(x, y); //true
     check_alias(x, z); //false
     check_alias(y, z);  //false
     check_alias(x, w); // true;
     check_alias(x, zz); // false
     check_alias(z, zz); //false
     
     System.out.println("-----TEST1 DONE------");
   }
   
    public static void test2() {
     Object o1 = new Object();
     Object o2 = new Object();
     Object o3 = new Object();
     A v1 = new A(o1, o2);
     A v2 = new B(o1, o2, o3);
     A v3 = new C(o1, o2, o3);
     A v4 = new D(o1, o2, o3);
     A v5 = new E(o1, o2, o3);
     test2_helper(v1, v2, v3, v4, v5);

   }
   
   
   public static void test2_helper(A v1, A v2, A v3, A v4, A v5) {
     Object a = v1.goo(); // o1
     Object b = v2.goo(); // o3
     Object c = v3.goo(); // o3
     Object d = v4.goo(); // new object
     Object e = v5.goo(); // o1
     
     check_alias(a, b); // false
     check_alias(a, c); // false
     check_alias(a, d); // false
     check_alias(a, e); // true
     check_alias(b,c); // true
     check_alias(b,d); // false
     check_alias(b,e); // false
     check_alias(c,d); //false
     check_alias(c,e); // false
     check_alias(d,e); //false
     

     
    System.out.println("-----TEST2 PART 1 DONE------");
    
    Object f = v3.goo2();
    Object g = v5.goo2();
    Object h = v3.goo3();
    check_alias(a, f); // false
    check_alias(a, g); // true
    check_alias(a, h); // false
    check_alias(b, f); // true
      
   }
   
   public static void check_alias(Object x, Object y){
      if(x == y) System.out.println("Alias");
      else System.out.println("Don't alias");
   }
   
   //--------------------------------------------
   
   A(Object a, Object b) {
     this.f = a;
     this.g = b;
   }
   
   Object bar() {
     return foo();
   }
   
   Object foo() {
    return this.f;
   }

   
   Object goo() {
    return foo();
   }
   
   Object goo2() {
     return this.f;
   }
   
   Object goo3() {
    return goo2();
   }
   
   
   
}