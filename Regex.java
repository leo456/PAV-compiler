/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.*;
import java.util.regex.Pattern;



/**
 *
 * @author Suleman
 */
public class Regex {

    /**
     * @param args the command line arguments
     */
  static  String cls_pt;
    static String val_pt;
    public static void main(String[] args)  {
        // TODO code application logic here

      // Scanner f = new Scanner(new FileReader("input.txt"));
      
      // for Syntax
      ArrayList<syn_vl> snx  = new ArrayList<syn_vl>();
      
      
      //-----------------------
       int i ,l_DT ,index ,line_num = 1;
       String thisLine="" ,temp = "",id_temp="", op_temp="",con_temp="",str;
       boolean type_dt, type_k , kfound =false,dfound =false,opfound =false;
       int k = 1,fwd;
       String[] id_arr  = {"for","break","return","in","if","else","elif","continue","do","while","class","public","private","new","create"};
       String[] op_arr = {"INC_DEC","PM","MDM","ROP","Bitwise","LOG_OR","LOG_AND","LOG_NOT","ASOP","SHIFT","AOP"};
       String[] con_arr = {"int_const", "String_const","char_const","float_const","Invalid_lexene"};
       String[] p_arr = {"(",")","{","}","[","]","comma",":"};
// boolean[] k_words = new boolean[4];
    //   boolean[] d_t = new boolean[5];
       datatypes dt = new datatypes();
       keyword k_words = new keyword();
       Identifier id = new Identifier();
       operators op = new operators();
       constants ct = new constants();
       punctuators pnt = new punctuators();
       File SOT=new File("tokens.txt");
       try{
         java.io.PrintWriter out=new java.io.PrintWriter(SOT);
      
     try{
         // open input stream test.txt for reading purpose.
         BufferedReader br = new BufferedReader(new FileReader("input.txt"));
         
              while ((thisLine = br.readLine()) != null) {
             String s = thisLine;
             char[] c_a = s.toCharArray();
             int l = c_a.length;
             int w = 0;
              //l_DT = d_t.length;
       while(w < l)
       {
              temp += c_a[w]; 
//       
//            type_dt = dt.chk_dt(s);
           fwd =w + 1;
//           String f="";
//                   f+=c_a[fwd]; 
          // str ="in";
          if(temp.equalsIgnoreCase("$"))
          {
               cls_pt ="EndMarker"; 
               val_pt = temp;
               syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
               out.println(cls_pt+" , "+val_pt+" , "+line_num);
          //w--;
          snx.add(ob);
          temp="";
          
              // break;
          } 
           if(temp.equalsIgnoreCase("."))
          {
               cls_pt ="DOT"; 
               val_pt = temp;
               syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
               out.println(cls_pt+" , "+val_pt+" , "+line_num);
          //w--;
          snx.add(ob);
          temp="";
          
              // break;
          } 
           if(temp.equalsIgnoreCase("//"))
           {
               temp = "";
               break;
           }
           else if(temp.equalsIgnoreCase("int")&&c_a[fwd]!=' ')
            {}
           else if(k_words.chk_keyword(temp))
           { 
               if(w==l-1)
           {
               cls_pt =id_arr[k_words.getValue()]; 
               val_pt = temp;
               out.println(cls_pt+" , "+val_pt+" , "+line_num);
               //snx.add();
        syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
        snx.add(ob);       
        temp = "";
               dfound = true;
               break;
           }
              else if(temp.equalsIgnoreCase("in")&&c_a[fwd]!=' '||temp.equalsIgnoreCase("do")&&c_a[fwd]!=' '||temp.equalsIgnoreCase("class")&&c_a[fwd]!=' '||temp.equalsIgnoreCase("for")&&c_a[fwd]!=' '||temp.equalsIgnoreCase("break")&&c_a[fwd]!=' '||temp.equalsIgnoreCase("return")&&c_a[fwd]!=' '||temp.equalsIgnoreCase("if")&&c_a[fwd]!=' '||temp.equalsIgnoreCase("else")&&c_a[fwd]!=' '||temp.equalsIgnoreCase("elif")&&c_a[fwd]!=' '||temp.equalsIgnoreCase("continue")&&c_a[fwd]!=' '||temp.equalsIgnoreCase("while")&&c_a[fwd]!=' '||temp.equalsIgnoreCase("public")&&c_a[fwd]!=' '||temp.equalsIgnoreCase("__")&&c_a[fwd]!=' ')//||temp.equalsIgnoreCase("do")&&c_a[fwd]!="s"||temp.equalsIgnoreCase("class"))
               {}
           else{
               cls_pt =id_arr[k_words.getValue()]; 
               val_pt = temp;
               out.println(cls_pt+" , "+val_pt+" , "+line_num);
               syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
        snx.add(ob);
               temp = "";
               dfound = true;
           }
           }
           else if(dt.chk_dt(temp))
            {
                if(w == l-1)
                {cls_pt ="DT"; 
               val_pt = temp;
               out.println(cls_pt+" , "+val_pt+" , "+line_num);
               syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
        snx.add(ob);
               temp = "";
               dfound = true;
                break;}
                else{cls_pt ="DT"; 
               val_pt = temp;
               out.println(cls_pt+" , "+val_pt+" , "+line_num);
               syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
        snx.add(ob);
               temp = "";
               dfound = true;}
//cls_pt ="DT"; 
//               val_pt = temp;
//               System.out.println(cls_pt+" , "+val_pt+" , "+line_num);
//               temp = "";
//               dfound = true;
            }  
             else if(temp.equalsIgnoreCase(" "))
            {
                temp = "";
               //out.println("space");
            }
             if(op.chk_op(temp)== true || op.chk_op(temp)==false )
            {
//                 cls_pt =op_arr[op.getValue()]; 
//                     val_pt = temp;
//                      System.out.println(cls_pt+" , "+val_pt+" , "+line_num);
//                       temp = "";
                       if(op.chk_op(temp)==true)
                {
                op_temp = temp;
            }
                     else //   if(op.chk_op(temp)==false)
              {
//                  if(w==l-1)
//                      op_temp = temp;
                  if(op.chk_op(op_temp)==true)
                  {
                     cls_pt =op_arr[op.getValue()]; 
                     val_pt = op_temp;
                      out.println(cls_pt+" , "+val_pt+" , "+line_num);
                     syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
        snx.add(ob);
                      temp = "";
                      w--;
                      if(w==l-1)
                      {
                          temp += c_a[w];
                          if(op.chk_op(temp)) {
                             cls_pt =op_arr[op.getValue()];
                              val_pt = temp;
                              out.println(cls_pt+" , "+val_pt+" , "+line_num);
                      syn_vl ob1 = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
        snx.add(ob);          
                              temp = "";
                              opfound  = true;
                          }
                          break;
                      }
                      else
                     // w--;
                       opfound  = true;
                    
                      
//                  if(w==l-1)//&& !temp.equalsIgnoreCase(" "))
//                  {
//                      cls_pt =op_arr[op.getValue()]; 
//                     // if(op.chk_op(op_temp+c_a[fwd]))
//                     val_pt = op_temp ;//+ c_a[w];
////                     else
////                      {val_pt = op_temp;
////                     // w--;
////                      }
//               out.println(cls_pt+" , "+val_pt+" , "+line_num);
//                       temp = "";
//                       opfound = true;
//                    //   break;
//                  }
//                  else{ 
//                     cls_pt =op_arr[op.getValue()]; 
//                     val_pt = op_temp;
//                      out.println(cls_pt+" , "+val_pt+" , "+line_num);
//                       temp = "";
//                       w--;
//                       opfound  = true;
//                  }
                  }
              }
            
             op_temp = temp;
            }           
            
              if(ct.chk_constant(temp)==true||ct.chk_constant(temp)== false)
           {
               
//               cls_pt =con_arr[ct.getValue()]; 
//                     val_pt = temp;
//                      System.out.println(cls_pt+" , "+val_pt+" , "+line_num);
//                       temp = "";
if(ct.lexine(temp)==true)
{
    cls_pt = con_arr[ct.getValue()]; 
                     val_pt = temp;
                      out.println(cls_pt+" , "+val_pt+" , "+line_num);
                      syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
        snx.add(ob); 
                      temp = "";
                       break;
}
               if(w==l-1)
               {
                   if(ct.chk_constant(temp))
                   {
                    cls_pt =con_arr[ct.getValue()]; 
                     val_pt = temp;
                      out.println(cls_pt+" , "+val_pt+" , "+line_num);
                      syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
        snx.add(ob); 
                      temp = "";
                     // w--;
// break;
                   }
                   else if(ct.chk_constant(con_temp))
                   {
                                cls_pt =con_arr[ct.getValue()]; 
                     val_pt = con_temp;
                      out.println(cls_pt+" , "+val_pt+" , "+line_num);
                      syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
        snx.add(ob); 
                      temp = "";
//                       break;
 w--;
                   }
               }
                 else if(ct.chk_constant(temp)==false)
              {
                  if(ct.chk_constant(con_temp)==true)
                  {
                      cls_pt =con_arr[ct.getValue()]; 
                     val_pt = con_temp;
                      out.println(cls_pt+" , "+val_pt+" , "+line_num);
                      syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
        snx.add(ob);
                      temp = "";
                    w--;
                  } 
           }
              con_temp = temp;          
           } 
           if(pnt.chk_punct(temp))
            {
                 cls_pt =p_arr[pnt.getValue()]; 
                     val_pt = temp;
                      
                     out.println(cls_pt+" , "+val_pt+" , "+line_num);
                     syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
        snx.add(ob);
                     temp ="";
                  
            }
           else if( w==l-1 && pnt.chk_punct(temp))
           {
               cls_pt =p_arr[pnt.getValue()]; 
                     val_pt = temp;
                      
                     out.println(cls_pt+" , "+val_pt+" , "+line_num);
                     syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
        snx.add(ob);
                     temp =" ";
           }
             else if(temp.equalsIgnoreCase(" "))
            {
                temp = "";       
            }
           else if(id.chk_identifier(temp)==true || id.chk_identifier(temp)==false)
            {
                if(dfound || kfound || opfound)
                { id_temp = temp;
                dfound =false;
                kfound = false;
                opfound =false;
                }
                
                if(id.chk_identifier(temp)==false||w ==l-1)
              {
                  if(w==l-1&& id_temp.equalsIgnoreCase("")&&id.chk_identifier(temp))
                  {
                      cls_pt ="ID"; 
                       
//                      if(id.chk_identifier(val_pt+c_a[w]))
//                    val_pt = id_temp + c_a[w];
//                      else 
                          val_pt = temp;
//                     if(val_pt.equalsIgnoreCase(""))
//                     {}
//                     else
                      out.println(cls_pt+" , "+val_pt+" , "+line_num);
                     syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
        snx.add(ob);
                     temp = "";
                  }
                  else
                  if(id.chk_identifier(id_temp)==true)
                  {  cls_pt ="ID"; 
                     val_pt = id_temp;
                      out.println(cls_pt+" , "+val_pt+" , "+line_num);
                     syn_vl ob = new syn_vl();
        ob.cls_pt = cls_pt;
        ob.val_pt = val_pt;
        ob.line_pt = line_num;
        snx.add(ob);
                      temp = "";
                       w--;
                  }
              }
              
              id_temp = temp;
              
            }
//           else if(temp.equalsIgnoreCase("."))
//          {
//               cls_pt ="DOT"; 
//               val_pt = temp;
//               out.println(cls_pt+" , "+val_pt+" , "+line_num);
//          } 
           else if(temp.equalsIgnoreCase(" "))
            {
                temp = "";
               // out.println("space");
            }
           
          //  id_temp += temp;
//            op_temp += temp;
            w++;
       }
      
       line_num++;
       temp ="";
            // System.out.println("lin"+line_num);
         }   
         out.close();
         syntax syn = new syntax(snx);
      }catch(Exception e){
         e.printStackTrace();
      }
       }
        catch(Exception e){
           System.out.println(e);
       }
//out.close();

        
    }

    public static boolean Dtypes (boolean t, String v)
    {
        if(t==true)
        {
            cls_pt ="DT"; 
            val_pt = v;
            return true;
        }
        return false;
    }
    public static boolean Ktypes (String a)
    {
//        Pattern p = Pattern.compile("a|b");
//         Matcher m = p.matcher(a);
         Pattern p = Pattern.compile("[A-Z]+|[a-z]");
        Matcher m = p.matcher(a);
      if(m.matches())  
      return true;
      else
     return false;
         
    }

}
//      Pattern p = Pattern.compile("a|b");
//      Matcher m = p.matcher("a");
//      if(m.matches()== true)  
//      System.out.println("valid");
//      else
//            System.out.println("invalid");
//    }
    

