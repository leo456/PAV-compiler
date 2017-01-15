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
public class punctuators {
int value;
    public boolean chk_punct(String in) {

        if (in.contentEquals("(")) {
            value = 0 ;
            return true;
        }
        if (in.contentEquals(")")) {
            value = 1 ;
            return true;
        }
        if (in.contentEquals("{")) {
           value = 2 ;
            return true;
        }
        if (in.contentEquals("}")) {
            value = 3 ;
            return true;
        }
        if (in.contentEquals("[")) {
            value = 4;
            return true;
        }
        if (in.contentEquals("]")) {
            value = 5;
            return true;
        }
        if (in.contentEquals(",")) {
        value = 6;
            return true;
        }
        if (in.contentEquals(":")) {
            value = 7 ;
            return true;
        }
        return false;

    }
    public int getValue()
    {
        return value;
    }
}
