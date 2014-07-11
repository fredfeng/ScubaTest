 
public class E extends C { 

    
   
   E(Object a, Object b){
     super(a, b);
   }
   
   E(Object x, Object y, Object z) {
     super(x, y);
     this.b = new Object();
   }
   
   Object goo() {
     this.b = this.f;
     return this.b;
   } 


 

   
   
   
}