/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Suleman
 */
public class operators {
  int value;
  
    public boolean chk_op(String input)
  {
    // String i= null;
        if(input.equals("+") ||input.equals("-")  )
        {
//        System.out.print(" \n PM");
               value = 1;    
        return true;
        }
        else if(input.equals("/")|| input.equals("%")||input.equals("*")){
            value = 2; 
//            System.out.print(" \n DMOP");
            return true;
        }
       
        else if(input.equals("++") || input.equals("--")  )
        {
//         System.out.print(" \n INDECCOP \n");
           value = 0;
         return true;
        
        }
        else if(input.equals("<") || input.equals(">")  || input.equals("<=")  || input.equals(">=")  || input.equals("==") || input.equals("!="))
        {
             value = 3;
//        System.out.print("\n REOP \n");
        return true;
        }
        else if(input.equals("|") || input.equals("&")|| input.equals("~"))
        {
        value = 4;
//            System.out.print("\n bitwise \n");
        return true;
            
        }
        else if(input.equals("||"))
        {
            value = 5;
//        System.out.print("\n LOG_OR \n");
        return true;
            
        }
         else if(input.equals("&&"))
        {
            value = 6;
//        System.out.print("\n LOG_AND \n");
        return true;
            
        }
        else if( input.equals("!"))
        {
            value = 7;
//        System.out.print("\n LOG_NOT \n");
        return true;
            
        }
        else if(input.equals("+=")|| input.equals("-=") || input.equals("%=")|| input.equals("/=") || input.equals("*=") || input.equals("&=") || input.equals("|=")|| input.equals("<<=")|| input.equals(">>="))
        {
//             System.out.print("\n ASOP \n");
       value = 8;
             return true;
        }
         else if(input.equals("<<") || input.equals(">>"))
        {
            value = 9;
             //System.out.print("\n SHIFT \n");
        return true;
        }
        
        else if(input.equals("=")   )
        {
           //  System.out.print("\n AOP ");
        value = 10;
             return true;
        }
        else
        { //   System.out.print("\n invalid  lex");
        return false;}
      
      
//      if (INC_DEC(s)) {
//          value = 0;
//          return true;
//      }
//       if (ASOP(s)) {
//          value = 7;
//          return true;
//      }
//      if (PM(s)) {
//          value = 1;
//          return true;
//      }
//      if (MDM(s)) {
//          value = 2;
//          return true;
//      }
//      if (ROP(s)) {
//          value = 3;
//          return true;
//      }
//      if (LOAND(s)) {
//          value = 4;
//          return true;
//      }
//      if (LONOT(s)) {
//          value = 5;
//          return true;
//      }
//      if (LONT(s)) {
//          value = 6;
//          return true;
//      }
//
//      if (AOP(s)) {
//          value = 8;
//          return true;
//      }
//      if (AND(s)) {
//          value = 9;
//          return true;
//      }
//      if (OR(s)) {
//          value = 10;
//          return true;
//      }
//      if (SHIFT(s)) {
//          value = 11;
//          return true;
//      }
//      if (SOP(s)) {
//          value = 12;
//          return true;
//      }
      
      
//return false;
  } 
    public int getValue()
    {
        return value;
    }
  public boolean INC_DEC(String in){
       Pattern p = Pattern.compile("[+|-]{2}");
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
          return true;
     //return 1;
   return false; 
}
    public boolean PM(String s){
       Pattern p = Pattern.compile("[+ -]");
     Matcher m = p.matcher(s);
     if(m.matches()== true)  
     return true;
//     System.out.println("valid");
//     else
//           System.out.println("invalid");
//   //return 0;
return false; 
   }
   
   boolean MDM(String in){
      // String s = in;
       Pattern p = Pattern.compile("[/*]|[/]|[%]");
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
    return true;
     //return 1;
   return false; 
   }
   
   
   boolean ROP(String in){
       
     
       String s = in;
       Pattern p = Pattern.compile("[< > <= >= != ==]{1,2}");
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
    return true;
     //return 1;
   return false; 
   }
   
   boolean LOAND(String in){
       
     
       String s = in;
       Pattern p = Pattern.compile("&&");
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
    return true;
     //return 1;
   return false; 
   }
   boolean LONOT(String in){
       
     
       String s = in;
       Pattern p = Pattern.compile("!!");
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
   return true;
     //return 1;
   return false; 
   }
   
   boolean LONT(String in){

       Pattern p = Pattern.compile("!");
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
    return true;
     //return 1;
   return false; 
   }
     
   boolean ASOP(String in){
int a= 2;

     Pattern p = Pattern.compile("[+=] | [-=] | [*=] | [%=] "); // no divide
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
    return true;
     //return 1;
   return false; 
   }
   
   boolean AOP(String in){

       Pattern p = Pattern.compile("=");
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
    return true;
     //return 1;
   return false; 
   }
   
   boolean AND(String in){

       Pattern p = Pattern.compile("&");
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
    return true;
     //return 1;
   return false; 
   }
     
   boolean OR(String in){
       Pattern p = Pattern.compile("[|]{1,2}");
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
    return true;
     //return 1;
   return false; 
   }
   
   boolean SHIFT(String in){
       Pattern p = Pattern.compile("~");
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
    return true;
     //return 1;
   return false; 
   }
   
//   boolean SOP (String in){
//       Pattern p = Pattern.compile("<+");
//     Matcher m = p.matcher(in);
//     if(m.matches()== true)  
//    return true;
//     //return 1;
//   return false; 
//   }
   
  
}