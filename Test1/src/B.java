 
public class B extends A { 

   Object b;
   
   B(Object a, Object b){
     super(a, b);
     this.b = new Object();
   }
   
   B(Object x, Object y, Object z) {
     super(x, y);
     this.b = z;
   }
   
   Object baz() {
     this.b = this.f;
     return new Object();
   }
   
   Object foo() {
     Object t = baz();
     return b;
   }
   
   Object goo() {
     return this.b;
   }
   
   Object goo2() {
     return this.b;
   }
   
   
   
   
 

   
   
   
}