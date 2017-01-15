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
 * @author Hp
 */
public class constant {
    int value;
    public boolean chk_constant(String a)
     {    
      if(intconstant(a)){
          value = 0;
          return true;
      }
      
      if(Stringconstant(a)){
        value = 3;
        return true;
    }
      if(charconstant(a)){
           
           value = 1;
           return true;
           }
      
    if(floatconstant(a)){
        value = 2;
        return true;
    }  
      
    
    //if(P1(s)){
      //  value = 17;
        //return true;
    //}
      
return false;
  } 
    
    public int getValue()
    {
        return value;
    }

    
     public boolean intconstant(String in){
       
     //String s = n;
       Pattern p = Pattern.compile("[0-9]*");    
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
     //{value=0;
         
     return true;
         
   return false;   
    }
    
public boolean Stringconstant(String in){
       
   //  String s = n;
      Pattern p = Pattern.compile("\".*\"");    
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
     //{value=0;
     {         
     return true;
     }     
   return false;   
    }     

     public boolean charconstant(String in){
       
     //String s = n;
       Pattern p = Pattern.compile("('.')|('')");    
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
     //{value=0;
     {    
     return true;
     }     
   return false;   
    }


    
    
    
    
    public boolean floatconstant(String in){
 
        //int value;
        
     //String s = n;
       Pattern p = Pattern.compile("[+-]?[0-9]{0,}\\.[0-9]{0,}([eE][+-]?[0-9]{0,})?");    
     Matcher m = p.matcher(in);
     if(m.matches()== true)  
     //{value=0;
     {     
     return true;
     }     
   return false;   
    }
   
}