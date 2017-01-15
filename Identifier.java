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
public class Identifier {
    public boolean chk_identifier(String s)
{
    Pattern p = Pattern.compile("((([a-z]+|[A-Z]+)+)((_)|[0-9])*)+");
    Matcher m = p.matcher(s);
//        String[] arr = new String[4];
//        arr[0] = "int";
//        arr[1] = "int";
//        arr[2] = "double";
//        arr[3] = "char";
//for(int i = 0 ; i < arr.length;i++)
//{
//    if(s.equalsIgnoreCase(arr[i]))
//        return false;
//}
    if (m.matches() == true) {
        return true;
    }

    return false;
}
}
