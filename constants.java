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
public class constants {
    int value;
    public boolean chk_constant(String a) {
        if (intconstant(a)) {
            value = 0;
            return true;
        }
        if (Stringconstant(a)) {
            value = 1;
            return true;
        }
        if (charconstant(a)) {
            value = 2;
            return true;
        }
        if (floatconstant(a)) {
            value = 3;
            return true;
        } 
        if (lexine(a))
        {    value = 4;
            return true;
        }
 
        return false;
    }

    public int getValue() {
        return value;
    }

    public boolean intconstant(String in) {

        //String s = n;
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(in);
        if (m.matches() == true) //{value=0;    
        {
            return true;
        }

        return false;
    }

    public boolean Stringconstant(String in) {

        //  String s = n;
        Pattern p = Pattern.compile("\".*(\"|\".*\")"); ///  //^\".*?\"$
        Matcher m = p.matcher(in);
        if (m.matches() == true) //{value=0;
        {
            return true;
        }
        return false;
    }

    public boolean charconstant(String in) {

        //String s = n;
        Pattern p = Pattern.compile("('.')|('')");
        Matcher m = p.matcher(in);
        if (m.matches() == true) //{value=0;
        {
            return true;
        }
        return false;
    }

 public boolean lexine (String in) {
        //int value;
        //String s = n;
        Pattern p = Pattern.compile(" \\.[0-9]+[A-Z]+[a-z]6");
        Matcher m = p.matcher(in);
        if (m.matches() == true) //{value=0;
        {
         return true;
        } //else {
//            Pattern p1 = Pattern.compile("[+-]?[0-9]{0,}\\.[0-9]{0,}([eE][+-]?[0-9]{0,})?");
//            Matcher m1 = p1.matcher(in);
//            if (m1.matches() == true) //  value = 4;
//            {
//                
//            }
//        }
        return false;   
    }

public boolean floatconstant(String in) {
        //int value;
        //String s = n;
        Pattern p = Pattern.compile("[0-9]+\\.[0-9]*");
        Matcher m = p.matcher(in);
        if (m.matches() == true) //{value=0;
        {
         return true;
        } //else {
//            Pattern p1 = Pattern.compile("[+-]?[0-9]{0,}\\.[0-9]{0,}([eE][+-]?[0-9]{0,})?");
//            Matcher m1 = p1.matcher(in);
//            if (m1.matches() == true) //  value = 4;
//            {
//                
//            }
//        }
        return false;   
    }
}
