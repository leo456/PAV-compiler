/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

/**
 *
 * @author Suleman
 */
public class keyword {
    int value;
     public boolean chk_keyword(String in)
     {
         
         if(in.contentEquals("for"))
         { 
             value= 0;
             return true;
         }
         if(in.contentEquals("break"))
         {
             value= 1;
             return true;
         }
         if(in.contentEquals("return"))
         {
             value = 2;
             return true;
         }
         if(in.contentEquals("in"))
         {
             value = 3;
             return true;
         }
         if(in.contentEquals("if"))
         {  
             value = 4;
             return true;
         }
         if(in.contentEquals("else"))
         {
             value = 5;
             return true;
         }
         if(in.contentEquals("elif"))
         {
             value = 6;
             return true;
         }
         if(in.contentEquals("continue"))
         {
             value = 7;
             return true;
         }
         if(in.contentEquals("do"))
         {
             value = 8;   
             return true;
         }
         if(in.contentEquals("while"))
         {
             value = 9;
             return true;
         }
         if(in.contentEquals("class"))
         {
             value = 10;
             return true;
         }
         if(in.contentEquals("public"))
         {
             value = 11;
             return true;
         }
         if(in.contentEquals("__"))
         {
             value = 12;
             return true;
         }
         if(in.contentEquals("new"))
         {
             value = 13;
             return true;
         }
         if(in.contentEquals("create"))
         {
         value = 14;
         return true;
         }
         
        return false;
     }
     public int getValue()
     {
         return value;
     }
}
