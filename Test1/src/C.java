 
public class C extends B { 

    
   
   C(Object a, Object b){
     super(a, b);
   }
   
   C(Object x, Object y, Object z) {
     super(x, y, z);
   }

   Object baz() {
     return this.b;
   }
   
   Object foo() {
     return baz();
   }
   
   Object goo() {
     this.f = this.b;
     return this.b;
   }
   

 

   
   
   
}