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
public class datatypes {

    public boolean chk_dt(String in) {
        if(in.contentEquals("int"))
         {
             return true;
         }
         if(in.contentEquals("double"))
         {
             return true;
         }
         if(in.contentEquals("float"))
         {
             return true;
         }
         if(in.contentEquals("char"))
         {
             return true;
         }
         if(in.contentEquals("String"))
         {
             return true;
         }
        return false;
    }
}

