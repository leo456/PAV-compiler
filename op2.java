/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

/**
 *
 * @author Hp
 */
public class op2 {
    
     String IsOperators(String input)
    {
        String i= null;
        if(input.equals("+") ||input.equals("-")  )
        {
        System.out.print(" \n PMOP");
            return "true";
        }
        else if(input.equals("/")|| input.equals("%")){
              System.out.print(" \n DMOP");
            return "true";
        }
         else if(input.equals("*")){
              System.out.print(" \n MOP \n");
            return "true";
        }
        else if(input.equals("++") || input.equals("--")  )
        {
         System.out.print(" \n INDECCOP \n");
         return "true";
        
        }
        else if(input.equals("<") || input.equals(">")  || input.equals("<=")  || input.equals(">=")  || input.equals("==") || input.equals("!="))
        {
        System.out.print("\n REOP \n");
        return "true";
        }
        else if(input.equals("||") || input.equals("&&")|| input.equals("!"))
        {
        System.out.print("\n LOP \n");
        return "true";
            
        }
        else if(input.equals("+=")|| input.equals("-=") || input.equals("%=")|| input.equals("/=") || input.equals("*=") )
        {
             System.out.print("\n ASOP \n");
        return "true";
        }
         else if(input.equals("<<") || input.equals(">>")  )
        {
             System.out.print("\n SOP \n");
        return "true";
        }
        else if(input.equals("~")   )
        {
             System.out.print("\n SHIFTOP ");
        return "true";
        }
        
        else if(input.equals("=")   )
        {
             System.out.print("\n AOP ");
        return "true";
        }
        System.out.print("\n invalid  lex");
        return "false";
        
    }
        
    
}
