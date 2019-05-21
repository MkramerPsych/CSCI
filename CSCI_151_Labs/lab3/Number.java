public class Number implements Token, Expression {
   
	//Instance variables
   int number;
   
   //Constructors
   public Number(int number){
      this.number = number;
   }
      
   //Methods
   public boolean isNumber(){
      return true;
   }
   
   public boolean isOperator(){
      return false;
   }
   
   public int valueOf() { //returns value of number
	   return number;
   }
   
   public String toInfix() {
	   return Integer.toString(number);
   }
   
   public String toPrefix() {
	   return Integer.toString(number);
   }
   
   public String toPostfix() {
	   return Integer.toString(number);
   }
   
   public String toString() {
	   return Integer.toString(number);
   }
}