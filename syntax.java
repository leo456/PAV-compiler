/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Suleman
 */
public class syntax {
  
    ArrayList<memberTable> met = new ArrayList<memberTable>();
    memberTable me = new memberTable();
    
    ArrayList<methodTable> mad = new ArrayList<methodTable>();
    methodTable md = new methodTable();
    
    classTable ct = new classTable();
    ArrayList<classTable> cat = new ArrayList<classTable>();
    
    Stack  scope_stack = new Stack();
    int  ScopeCount = 0;
    int scope =0;
    constants o1 = new constants();
    operators p = new operators();
     int i=0;
   
    ArrayList<syn_vl> arr = new ArrayList<syn_vl>();

    syntax(ArrayList arr_syn_vl) {
        arr = arr_syn_vl;
        print();
        validate();
    }

     public int createScope()
        {
            scope_stack.push(ScopeCount);
            ScopeCount++;
            return (int) scope_stack.peek();
        }
        public void DestroyScope()
        {
            scope_stack.pop();
        }
    public String Clookup(String name) {
        for (int i = 0; i < cat.size(); i++) {

            if (cat.get(i).name == name) {
                return cat.get(i).type;
            }
        }
        return "null";
    }

    public void Cinsert(String name, String type, String AM, List<methodTable> methodLink) {
        ct.name = name;
        ct.type = type;
        ct.AM = AM;
        ct.methodLink = methodLink;
        cat.add(ct);
    }

    public String Mlookup(String name) {
        for (int i = 0; i < met.size(); i++) {

            if (met.get(i).name == name) {
                return met.get(i).type;
            }
        }

        return "null";
    }

    public void Minsert(String name, String type, String AM, int scope) {
        me.name = name;
        me.type = type;
        me.AM = AM;
        //  me.scope = scope;
        //ct.methodLink = methodLink;
        List<memberTable> MemberLink;
        met.add(me);
    }

    public String MDlookup(String name) {
        for (int i = 0; i < mad.size(); i++) {

            if (mad.get(i).name == name) {
                return mad.get(i).type;
            }
        }

        return "null";
    }

    public void MDinsert(String name, String type, String AM, int scope) {
        md.name = name;
        md.type = type;
        md.AM = AM;
        md.scope = scope;
        //ct.methodLink = methodLink;
        mad.add(md);
    }

void print() {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).cls_pt + "," + arr.get(i).val_pt + "," + arr.get(i).line_pt);
        }
    }

boolean validate() {
        if(Start())
        {
            System.out.println(" :- Success");
            return true;
        }
        return false;
    }

boolean clas() {

//        if (arr.get(i).cls_pt == "public"|| arr.get(i).cls_pt == "private") {
//            i++;
//            if (z()) {
//                //  i++;
//                if (arr.get(i).cls_pt == "{") {
//                    i++;
//                    if (class_d()) {
//                        if (arr.get(i).cls_pt == "}") {
//                            i++;
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
         if (arr.get(i).cls_pt.equalsIgnoreCase("class")) {
           i++;
             if (z()) {
                //i++;
                if (arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
                    i++;
                    if (arr.get(i).cls_pt.equalsIgnoreCase("{")) {
                        i++;
                        if (classbdy()) {
                            ///i++;
                            if (arr.get(i).cls_pt.equalsIgnoreCase("}")||arr.get(i).val_pt.equalsIgnoreCase("$")) {
                                //i++;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

boolean classbdy() {
        if(arr.get(i).cls_pt.equalsIgnoreCase("ID") && arr.get(i+1).cls_pt.equalsIgnoreCase("ID"))
        {
            if(object())
              {
                  i++;
                  if(classbdy())
                  {
                  //    i++;
                  return true;
                  }
              }
          }
        else if(arr.get(i).cls_pt.equalsIgnoreCase("ID")&& !arr.get(i).val_pt.equalsIgnoreCase("main")
                ||arr.get(i).cls_pt.equalsIgnoreCase("DT")  )
        {
            if(dec())
              {
                  if (classbdy()) {
//                    i++;
                    return true;
                }
            }
        }
        else if(arr.get(i).val_pt.equalsIgnoreCase("create"))
        {
            if(fn_create()) {
               i++;
                if (classbdy()) {
//                    i++;
                    return true;
                }

            }
        }
         else if (arr.get(i).cls_pt.equalsIgnoreCase("}")
                 ||arr.get(i).val_pt.equalsIgnoreCase("$")
                 ||arr.get(i).val_pt.equalsIgnoreCase("main")) 
         {
            return true;
        }
     
        return false;
    }

boolean object()
{
    
    if(arr.get(i).cls_pt.equalsIgnoreCase("ID"))//|| o1.chk_constant(arr.get(i).cls_pt)||arr.get(i).cls_pt == ",")
    {
//        if(arr.get(i).cls_pt == "ID")
//        {i++;
           i++;
            if(arr.get(i).cls_pt.equalsIgnoreCase("ID"))
            { i++;
                if(arr.get(i).val_pt.equalsIgnoreCase("="))
                { i++;
                  if(arr.get(i).cls_pt.equalsIgnoreCase("new"))
                  {i++;
                      if(arr.get(i).cls_pt.equalsIgnoreCase("ID"))
                      {i++;
                          if(arr.get(i).cls_pt.equalsIgnoreCase("("))
                          {i++;
                            if(PA())
                            {//i++;
                               return true;
                            }                             
                          }
                      }
                  }
                    
                }
            }
        }
    else if(arr.get(i).cls_pt.equalsIgnoreCase("}")
            ||arr.get(i).cls_pt.equalsIgnoreCase("DT")
            || arr.get(i).cls_pt.equalsIgnoreCase("ID")
            ||arr.get(i).cls_pt.equalsIgnoreCase("ID") && arr.get(i+1).cls_pt.equalsIgnoreCase("ID")
            ||arr.get(i).cls_pt.equalsIgnoreCase("create")
            ||arr.get(i).cls_pt.equalsIgnoreCase("class")
           || arr.get(i).cls_pt.equalsIgnoreCase("}")
//            ||arr.get(i).cls_pt.equalsIgnoreCase("DT")
//            || arr.get(i).cls_pt.equalsIgnoreCase("ID")
//            ||arr.get(i).cls_pt.equalsIgnoreCase("ID") && arr.get(i+1).cls_pt.equalsIgnoreCase("ID")
            ||arr.get(i).cls_pt.equalsIgnoreCase("for")
//            ||arr.get(i).cls_pt.equalsIgnoreCase("class")
            ||arr.get(i).cls_pt.equalsIgnoreCase("while")
                ||arr.get(i).cls_pt.equalsIgnoreCase("if")
                ||arr.get(i).cls_pt.equalsIgnoreCase("fn")            )
    
    {
        return true;
    }
    
    return false;
}

boolean q()
{
    if(arr.get(i).cls_pt.equalsIgnoreCase("ID")||o1.chk_constant(arr.get(i).val_pt)== true)
    { i++;
        if(A())
        { i++;
          if(M())
           {
          //  i++;
            return true;      
           }
        }
    
    }
    else if(arr.get(i).cls_pt.equalsIgnoreCase(")") )
      {
          return true;
       }
    else
        return false;
  
    return false;  
}

boolean A()
{
   if (o1.chk_constant(arr.get(i).cls_pt)==true)
   {  
       return true;
   }
   else if(arr.get(i).val_pt.equalsIgnoreCase(","))
       return true;
   
return false;
}

boolean M() {
        if (arr.get(i).val_pt.equalsIgnoreCase(",")) {
            i++;
            if (A()) {
                i++;
                if (M()) {
                        i++;
                } else if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
                    return true;
                }
            }
        } else if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
            return true;
        }

        return false;
    }

boolean dec()
{
    if(arr.get(i).cls_pt.equalsIgnoreCase("DT")||arr.get(i).cls_pt.equalsIgnoreCase("ID"))
    {
        if(arr.get(i).cls_pt.equalsIgnoreCase("DT"))
        {i++;
        if(arr.get(i).cls_pt.equalsIgnoreCase("ID"))
        {i++;
           if(AAA())
           {
               if(BBB())
               {
                   if(CCC())
                   {
                       return true;
                   }
               }
           }
        
        }
    }
    
    else if(arr.get(i).cls_pt.equalsIgnoreCase("ID"))
    {i++;
        if(AAA())
        {
            if(BBB())
            {
                if(CCC())
                {
                    return true;
                }
            }
        }
    }
   }
        else if(arr.get(i).cls_pt.equalsIgnoreCase("}")
            ||arr.get(i).cls_pt.equalsIgnoreCase("DT")
            || arr.get(i).cls_pt.equalsIgnoreCase("ID")
            ||arr.get(i).cls_pt.equalsIgnoreCase("ID") && arr.get(i+1).cls_pt.equalsIgnoreCase("ID")
            ||arr.get(i).cls_pt.equalsIgnoreCase("create")
                ||arr.get(i).cls_pt.equalsIgnoreCase("class"))
        {
            return true;
        }
    return false;
}

boolean AAA()
{
    if(ARR())
    {
        if(arr.get(i).val_pt.equalsIgnoreCase("}"))
        {
            return true;
        }
        else{
            i++;
        return true;
    }
    }
    else if(arr.get(i).val_pt.equalsIgnoreCase("=")||arr.get(i).val_pt.equalsIgnoreCase(",")
            ||arr.get(i).val_pt.equalsIgnoreCase("$")||arr.get(i).val_pt.equalsIgnoreCase("if") 
            || arr.get(i).cls_pt.equalsIgnoreCase("for")|| arr.get(i).val_pt.equalsIgnoreCase("while")
            || arr.get(i).cls_pt.equalsIgnoreCase("fn")|| arr.get(i).cls_pt.equalsIgnoreCase("DT") 
            || arr.get(i).cls_pt.equalsIgnoreCase("ID")||arr.get(i).cls_pt.equalsIgnoreCase("}")
            || arr.get(i).cls_pt.equalsIgnoreCase("create"))
    {
        return true;
    }
    
    return false;
} 

boolean BBB()
{
    if(arr.get(i).val_pt.equalsIgnoreCase("="))
    {i++;
        if(TTT())
        {
            return true;
        }
    }
    else if(arr.get(i).val_pt.equalsIgnoreCase(",")||arr.get(i).cls_pt.equalsIgnoreCase("}")
            ||arr.get(i).cls_pt.equalsIgnoreCase("!")||arr.get(i).cls_pt.equalsIgnoreCase("(")
            ||arr.get(i).cls_pt.equalsIgnoreCase("ID")||o1.chk_constant(arr.get(i).val_pt)
            ||arr.get(i).cls_pt.equalsIgnoreCase("DT")|| arr.get(i).cls_pt.equalsIgnoreCase("create"))
    {
        return true;
    }
    return false;
}

boolean TTT()
{
    if(BBDASH())
    {
        return true;
    }
    else if(arr.get(i).cls_pt.equalsIgnoreCase("ID")&& !arr.get(i).cls_pt.equalsIgnoreCase("main")
            ||arr.get(i).cls_pt.equalsIgnoreCase("}"))
    {i++;
        if(WW())
        {
            return true;
        }
    }
    return false;
}

boolean WW()
{
    if(BBB())
    {
        return true;
    }
    else if(arr.get(i).val_pt.equalsIgnoreCase(",")||arr.get(i).cls_pt.equalsIgnoreCase("}"))
    {
        return false;
    }
    return false;
}

boolean BBDASH()
{
    if(OEE()||arr.get(i).cls_pt.equalsIgnoreCase("String_const"))
    {
        return true;
    }
    else if(arr.get(i).cls_pt.equalsIgnoreCase("{"))
    {i++;
        if(DDD())
        {
            if(arr.get(i).val_pt.equalsIgnoreCase("}"))
            {i++;
                return true;
            }
        }
    }
    return false;
}

boolean CCC()
{
    if(arr.get(i).val_pt.equalsIgnoreCase(","))
    {i++;
        if(arr.get(i).val_pt.equalsIgnoreCase("ID"))
        {i++;
            if(AAA())
            {
                if(BBB())
                {
                    if(CCC())
                    {
                        return true;
                    }
                }
            }
        }
    }
    else if(arr.get(i).cls_pt.equalsIgnoreCase("DT")||arr.get(i).cls_pt.equalsIgnoreCase("ID")
            ||arr.get(i).val_pt.equalsIgnoreCase("$")||arr.get(i).cls_pt.equalsIgnoreCase("}")
            ||arr.get(i).cls_pt.equalsIgnoreCase("String_const")||arr.get(i).cls_pt.equalsIgnoreCase("float_const")
            ||arr.get(i).cls_pt.equalsIgnoreCase("char_const")||arr.get(i).cls_pt.equalsIgnoreCase("create"))
         {
             return true;
         }
    
    return false;
}

boolean DDD()
{
    if(curl())
    {
     if(M2())
     {
         return true;
     }
    }
    else if(arr.get(i).val_pt.equalsIgnoreCase("}"))
    {
        return true;
    }
    
    return false;
}

boolean M2()
{
    if(arr.get(i).val_pt.equalsIgnoreCase(","))
    {i++;
        if(curl())
        {
            if(M2())
            {
            return true;    
            }
            
        }
    }
    else if(arr.get(i).val_pt.equalsIgnoreCase("}"))
    {
        return true;
    }
    return false;
}

boolean BODY()
{
    if(arr.get(i).cls_pt.equalsIgnoreCase("for")
            || arr.get(i).val_pt.equalsIgnoreCase("while")
            || arr.get(i).val_pt.equalsIgnoreCase("if")
            ||arr.get(i).cls_pt.equalsIgnoreCase("fn")
            ||arr.get(i).cls_pt.equalsIgnoreCase("create")
            ||arr.get(i).cls_pt.equalsIgnoreCase("ID")
            ||arr.get(i).cls_pt.equalsIgnoreCase("DT"))
    {i++;
        if(MST())
        {
             return true;
        }
    }
    else if(arr.get(i).cls_pt.equalsIgnoreCase("}"))
    {
        return true;
    }
    
    return false;
}

boolean MST()
{
    if(arr.get(i).cls_pt.equalsIgnoreCase("for")||arr.get(i).val_pt.equalsIgnoreCase("while")||arr.get(i).val_pt.equalsIgnoreCase("if")||arr.get(i).cls_pt.equalsIgnoreCase("fn")||arr.get(i).cls_pt.equalsIgnoreCase("create")||arr.get(i).cls_pt.equalsIgnoreCase("ID")||arr.get(i).cls_pt.equalsIgnoreCase("DT"))
    {i++;
        if(SST())        {
             if(MST())
             {
            return true;
             }
        }
    }
    else if(arr.get(i).cls_pt.equalsIgnoreCase("}"))
    {
        return true;
    }
    
    return false;
}

boolean SST()
{
        if(arr.get(i).cls_pt.equalsIgnoreCase("for")||arr.get(i).val_pt.equalsIgnoreCase("while")||arr.get(i).val_pt.equalsIgnoreCase("if")||arr.get(i).cls_pt.equalsIgnoreCase("fn")||arr.get(i).cls_pt.equalsIgnoreCase("create")||arr.get(i).cls_pt.equalsIgnoreCase("ID")||arr.get(i).cls_pt.equalsIgnoreCase("DT"))
    {i++;
        if(for_st()|| while_st()||if_start()||fn_call()||fn_create()||object()||dec())
        {
             
            return true;
        }
    }
    
    return false;
}
boolean while_st() {
        if (arr.get(i).val_pt.equalsIgnoreCase("while")) {
            if (arr.get(i).val_pt.equalsIgnoreCase("while")) {
                i++;
                if (arr.get(i).cls_pt == "(") {
                    i++;
                    if (round()) {
                        if (arr.get(i).cls_pt == ")") {
                            i++;
                            if (arr.get(i).cls_pt == "{") {
                                i++;
                                if (BODY()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
      // return false;   
    }
        else if( arr.get(i).cls_pt.equalsIgnoreCase("}")
            ||arr.get(i).cls_pt.equalsIgnoreCase("DT")
            || arr.get(i).cls_pt.equalsIgnoreCase("ID")
            ||arr.get(i).cls_pt.equalsIgnoreCase("ID") && arr.get(i+1).cls_pt.equalsIgnoreCase("ID")
            ||arr.get(i).cls_pt.equalsIgnoreCase("for")
            ||arr.get(i).cls_pt.equalsIgnoreCase("class")
            ||arr.get(i).cls_pt.equalsIgnoreCase("while")
                ||arr.get(i).cls_pt.equalsIgnoreCase("if")
                ||arr.get(i).cls_pt.equalsIgnoreCase("fn")
                )
    {
        return true;
    }
        return false;
    }
  
    boolean if_start() {
        if (arr.get(i).val_pt.equalsIgnoreCase("if")) {
            if ((arr.get(i).val_pt.equalsIgnoreCase("if"))) {
                i++;
                if (arr.get(i).cls_pt.equalsIgnoreCase("(")) {
                    i++;
                    if (round()) {//i++;
                        if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
                            i++;
                            if (arr.get(i).cls_pt.equalsIgnoreCase("{")) {
                                i++;
                                if (BODY()) {//i++;
                                    if (arr.get(i).cls_pt.equalsIgnoreCase("}")) {
                                        i++;
                                        if(if_start())
                                        {
                                            if (elif_st()) {
                                            if (els()) {                 //                           
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        } else if (arr.get(i).cls_pt.equalsIgnoreCase("}")
                ||arr.get(i).cls_pt.equalsIgnoreCase("else")
                ||arr.get(i).val_pt.equalsIgnoreCase("elif")
                || arr.get(i).cls_pt.equalsIgnoreCase("for") 
                || arr.get(i).val_pt.equalsIgnoreCase("while")
                || arr.get(i).cls_pt.equalsIgnoreCase("fn") 
                || arr.get(i).cls_pt.equalsIgnoreCase("DT") 
                || arr.get(i).cls_pt.equalsIgnoreCase("ID")
//            ||    arr.get(i).cls_pt.equalsIgnoreCase("}")
//            ||arr.get(i).cls_pt.equalsIgnoreCase("DT")
//            || arr.get(i).cls_pt.equalsIgnoreCase("ID")
            ||arr.get(i).cls_pt.equalsIgnoreCase("ID") && arr.get(i+1).cls_pt.equalsIgnoreCase("ID")
            //||arr.get(i).cls_pt.equalsIgnoreCase("for")
            ||arr.get(i).cls_pt.equalsIgnoreCase("class")
            ||arr.get(i).val_pt.equalsIgnoreCase("$")
                ||arr.get(i).cls_pt.equalsIgnoreCase("if"))
//                ||arr.get(i).cls_pt.equalsIgnoreCase("fn")
                
    {
        return true;
    }
            
        
        return false;
    }

    boolean elif_st() {
        if (arr.get(i).val_pt.equalsIgnoreCase("elif")) {//i++;
            if (arr.get(i).val_pt.equalsIgnoreCase("elif")) {
                i++;
                if (arr.get(i).cls_pt.equalsIgnoreCase("(")) {
                    i++;
                    if (round()) {//i++;
                        if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
                            i++;
                            if (arr.get(i).cls_pt.equalsIgnoreCase("{")) {
                                i++;
                                if (BODY()) {//i++;
                                    if (arr.get(i).cls_pt.equalsIgnoreCase("}")) {
                                        i++;
                                        if (elif_st()) {
                                          // i++;
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (arr.get(i).val_pt.equalsIgnoreCase("else")||arr.get(i).val_pt.equalsIgnoreCase("}")
                ||arr.get(i).val_pt.equalsIgnoreCase("while")||arr.get(i).val_pt.equalsIgnoreCase("if")
                || arr.get(i).val_pt.equalsIgnoreCase("for")||arr.get(i).val_pt.equalsIgnoreCase("fn") 
                || arr.get(i).cls_pt.equalsIgnoreCase("return")|| arr.get(i).cls_pt.equalsIgnoreCase("ID")
                ||arr.get(i).cls_pt.equalsIgnoreCase("DT")||arr.get(i).val_pt.equalsIgnoreCase("$"))
        {  return true;
        }
        return false;
    }

 boolean els()
{
    if(arr.get(i).val_pt.equalsIgnoreCase("else"))
    {//i++;
        if(arr.get(i).val_pt.equalsIgnoreCase("else"))
        {i++;
            if(arr.get(i).cls_pt.equalsIgnoreCase("{"))
            {i++;
                if(BODY())
                {
                if(arr.get(i).cls_pt.equalsIgnoreCase("}"))
                {
                return true;
                }
                }
                
            }
        }
        }
    else if(arr.get(i).val_pt.equalsIgnoreCase("}")
     || arr.get(i).val_pt.equalsIgnoreCase("$"))
    {
        return true;
    }
    
    return false;
}

    boolean fn_create() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("create")) {
            i++;
            if (z()) {
                if (arr.get(i).cls_pt.equalsIgnoreCase("DT")) {
                    i++;
                    if (TT()) {
                        if (arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
                            i++;
                            if (arr.get(i).cls_pt.equalsIgnoreCase("(")) {
                                i++;
                                if (MM()) {
                                    if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
                                        i++;
                                        if (arr.get(i).cls_pt.equalsIgnoreCase("{")) {
                                            i++;
                                            if (body3()) {
                                              //  i++;
                                                if (return_st()) {
                                                    if (arr.get(i).cls_pt.equalsIgnoreCase("}") 
                                                            || arr.get(i).val_pt.equalsIgnoreCase("$")) {
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(arr.get(i).cls_pt.equalsIgnoreCase("}")
            ||arr.get(i).cls_pt.equalsIgnoreCase("DT")
            || arr.get(i).cls_pt.equalsIgnoreCase("ID")
            ||arr.get(i).cls_pt.equalsIgnoreCase("ID") && arr.get(i+1).cls_pt.equalsIgnoreCase("ID")
            ||arr.get(i).cls_pt.equalsIgnoreCase("create")
                ||arr.get(i).cls_pt.equalsIgnoreCase("class"))
        {
            return true;
        }
        return false;
    }

 
    boolean TT() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("[")) {
            i++;
            if (arr.get(i).cls_pt.equalsIgnoreCase("]")) {
                i++;
                return true;
            }
        } else if (arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
            return true;
        }

        return false;
    }
 
 boolean z()
{
    if(arr.get(i).cls_pt.equalsIgnoreCase ("private")||(arr.get(i).cls_pt.equalsIgnoreCase ("public")))
    {   i++;
        return true;
    }
    else if(arr.get(i).cls_pt.equalsIgnoreCase("ID")||arr.get(i).cls_pt.equalsIgnoreCase("DT"))
    {
        return true;
    }
    
    return false;
}

    boolean MM() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("DT")) {
            i++;
            if (TT()) {
              //  i++;
                if (arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
                    i++;
//        if(NN())
//        {//i++;
                    if (BB()) {
                        return true;
                    }
                }
            }
        } else if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
            return true;
        }

        return false;
    }

boolean BB() {
        if (arr.get(i).val_pt.equalsIgnoreCase(",")) {//i++;
            if (arr.get(i).val_pt.equalsIgnoreCase(",")) {
                i++;
                    if (MM()) {
return true;
                        //i++;
        //      
//                if (arr.get(i).cls_pt.equalsIgnoreCase("DT")) {
//                    i++;
//                    if (arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
//                        i++;
//                        if (NN()) {
//                            if (BB()) {
//                                return true;
//                            }
//                        }
//                    }
//                }
            }
        }       
        } else if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
            return true;
        }

        return false;
    
}

boolean NN() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("[")) {//i++;
            if (ARR()) {
                return true;
            }
        }else if(OEE())
        {
            return true;
        }
        else if (arr.get(i).cls_pt.equalsIgnoreCase("}")||arr.get(i).val_pt.equalsIgnoreCase(",")){
            return true;
        }

        return false;
    }

    boolean body3() {
        if (arr.get(i).val_pt.equalsIgnoreCase("if")||arr.get(i).val_pt.equalsIgnoreCase("while")
                || arr.get(i).val_pt.equalsIgnoreCase("for")||arr.get(i).val_pt.equalsIgnoreCase("fn") 
                || arr.get(i).cls_pt.equalsIgnoreCase("return")|| arr.get(i).cls_pt.equalsIgnoreCase("ID")
               || arr.get(i).cls_pt.equalsIgnoreCase("DT") ){// || arr.get(i).val_pt.equalsIgnoreCase("while")
         //|| arr.get(i).val_pt.equalsIgnoreCase("for") || arr.get(i).val_pt.equalsIgnoreCase("fn")
           //      || arr.get(i).cls_pt.equalsIgnoreCase("return") || arr.get(i).cls_pt.equalsIgnoreCase("ID"))
         if (dec()) {
                i++;
                if(body4())
                {
                return true;    
                }
                return true;
                
            }
         else if (for_st()) //||dec()
            {   i++;
                return true; //||if_start()||fn_call()|| return_st()||object()|| body4()
            } else if (while_st()) {
                i++;
                return true;
            } else if (if_start()) {
                i++;
                return true;
            } else if (fn_call()) {
                i++;
                return true;
            } else if (return_st()) {
                i++;
                return true;
            } else if (object()) {
                i++;
                return true;
            }
            else if (body4()) ///dec ka func bhi dena hai
            {
                i++;
                return true;

            }
        } else if (arr.get(i).cls_pt.equalsIgnoreCase("}")||arr.get(i).cls_pt.equalsIgnoreCase("create")) {
           
            return true;
        }

        return false;
    }

 boolean body4()
{
        if(body3())
        {i++;
             if(body4())
             {
             return true;
             }
        }
    else if(arr.get(i).cls_pt.equalsIgnoreCase("}")||arr.get(i).val_pt.equalsIgnoreCase("$"))
    {
        return true;
    }
    
    return false;
}
 
boolean return_st() {
        if (arr.get(i).val_pt.equalsIgnoreCase("return")) {
            if (arr.get(i).val_pt.equalsIgnoreCase("return")) {
                i++;
                if (arr.get(i).cls_pt.equalsIgnoreCase("(")) {
                   i++;
                    if (round()) {
                        if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
                            return true;
                        }
                    }

                }
            }
        }
        else if(arr.get(i).cls_pt.equalsIgnoreCase("}"))
        {
            return true;
        }
        return false;
    }

boolean cond() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("ID") ||arr.get(i).cls_pt.equalsIgnoreCase("int_const")){
            //i++;
            if (AEE()) {
               i++;
                if (arr.get(i).cls_pt.equalsIgnoreCase("ROP")) {
                    i++;
                    if (AEE()) {
                        if (zo()) {
            //                i++;
                            return true;
                        }
                        
                    }
                }
            }
        
    //return false;    
    }
return false;    
    }
boolean zo()
{
    if(arr.get(i).val_pt.equalsIgnoreCase("&&")||arr.get(i).val_pt.equalsIgnoreCase("||"))
    {
        if(arr.get(i).val_pt.equalsIgnoreCase("&&"))
        {i++;
            if( cond())
            {
             return true;   
            }
        }
        if(arr.get(i).val_pt.equalsIgnoreCase("||"))
        {i++;
            if( cond())
            {
            return true;    
            }
            
        }
    }
    
    return false;
}
    
 boolean AEE()
{
    if(arr.get(i).cls_pt.equalsIgnoreCase("int_const")||arr.get(i).cls_pt.equalsIgnoreCase("ID"))
    {
   if(arr.get(i).cls_pt.equalsIgnoreCase("int_const"))
    {i++;
        if(AE_C())
        {
            i++;
            return true;
        }
    }
   if(arr.get(i).cls_pt.equalsIgnoreCase("ID"))
    {i++;
        if(AE_C())
        {
            i++;
            return true;
        }
    }
    }
    
    return false;
}

 boolean AE_C()
{
    if(arr.get(i).cls_pt.equalsIgnoreCase("MDM")||arr.get(i).cls_pt.equalsIgnoreCase("PM")||arr.get(i).cls_pt.equalsIgnoreCase("int_const")||arr.get(i).cls_pt.equalsIgnoreCase("ID"))//||arr.get(i).cls_pt.equalsIgnoreCase("Int Const")||arr.get(i).cls_pt.equalsIgnoreCase("ID")))))
    {i++;
        if(OP())
        {
             if(AEE())
             {
                // i++;
             return true;
             }
        }
    }
  else if(arr.get(i).cls_pt.equalsIgnoreCase("OR")||arr.get(i).cls_pt.equalsIgnoreCase ("LOG_AND"))//||arr.get(i).cls_pt.equalsIgnoreCase("=>")||arr.get(i).cls_pt.equalsIgnoreCase("!="))//||arr.get(i).cls_pt.equalsIgnoreCase("$")||)
    {     
        return true;
    }     
    return false;
}
 
 boolean OP()
{
    if(arr.get(i).cls_pt.equalsIgnoreCase("MDM"))//||arr.get(i).cls_pt.equalsIgnoreCase("/")||arr.get(i).cls_pt.equalsIgnoreCase("*"))
    {i++;
        if( AEE())
        {
            i++;
             return true;
        }
    }
    if(arr.get(i).cls_pt.equalsIgnoreCase ("PM"))//||arr.get(i).cls_pt.equalsIgnoreCase("/")||arr.get(i).cls_pt.equalsIgnoreCase("*"))
    {i++;
        if( AEE())
        {
             i++;
             return true;
        }
    }
//   else if(arr.get(i).cls_pt.equalsIgnoreCase("$")||arr.get(i).cls_pt.equalsIgnoreCase("&&")||arr.get(i).cls_pt.equalsIgnoreCase("||")||arr.get(i).cls_pt.equalsIgnoreCase("==")||arr.get(i).cls_pt.equalsIgnoreCase("=<")||arr.get(i).cls_pt.equalsIgnoreCase("=>")||arr.get(i).cls_pt.equalsIgnoreCase("!="))
//  
//    {
//        return true;
//    }
  else if(arr.get(i).cls_pt.equalsIgnoreCase("int_const")||arr.get(i).cls_pt.equalsIgnoreCase("ID"))//||arr.get(i).cls_pt.equalsIgnoreCase("=>")||arr.get(i).cls_pt.equalsIgnoreCase("!="))//||arr.get(i).cls_pt.equalsIgnoreCase("$")||)
    {
        i++;      
        return true;
    }  
    return false;
}

 boolean main_body() {
        if (arr.get(i).val_pt.equalsIgnoreCase("if") 
                || arr.get(i).cls_pt.equalsIgnoreCase("for") 
                || arr.get(i).val_pt.equalsIgnoreCase("while") 
                || arr.get(i).cls_pt.equalsIgnoreCase("fn") 
                || arr.get(i).cls_pt.equalsIgnoreCase("DT")
                || arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
            if (mst_main()) {
                return true;
            }

        }
        else if(arr.get(i).cls_pt.equalsIgnoreCase("}"))
        {
            return true;
        }

        return false;
    }

 boolean mst_main() {
        if (arr.get(i).val_pt.equalsIgnoreCase("if") 
                || arr.get(i).cls_pt.equalsIgnoreCase("for") 
                || arr.get(i).val_pt.equalsIgnoreCase("while") 
                || arr.get(i).cls_pt.equalsIgnoreCase("fn")
                || arr.get(i).cls_pt.equalsIgnoreCase("DT") 
                || arr.get(i).cls_pt.equalsIgnoreCase("ID") ) {
            if (sst_main()) {
                if (mst_main()) {
                    return true;
                }
            }
        } else if (arr.get(i).cls_pt.equalsIgnoreCase("}")) {
            return true;
        }

        return false;
    }

 boolean sst_main() {
        if (arr.get(i).val_pt.equalsIgnoreCase("if"))
        {
            if(if_start())
            {
                return true;
            }
        }
        else if(arr.get(i).cls_pt.equalsIgnoreCase("for"))
        {
            if(for_st())
            {
                return true;
            }
        }
        else if(arr.get(i).val_pt.equalsIgnoreCase("while"))
        {
            if(while_st())
            {
                return true;
            }
            
        }
        else if( arr.get(i).cls_pt.equalsIgnoreCase("fn"))
        {
            if(fn_create())
            {
                return true;
            }
        }
        else if(arr.get(i).cls_pt.equalsIgnoreCase("DT")||arr.get(i).cls_pt.equalsIgnoreCase("ID"))
        {
            if(object()){
            i++;return true;}
            else if(dec()){
                i++;return true;}
            
        }
        else if(arr.get(i).cls_pt.equalsIgnoreCase("class"))
        {
            if(clas())
            {
                return true;
            }
            
        }

        return false;
    }

    boolean Start() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("class")) {
            //i++;
            if (clas_m()) {
                i++;
                if (clasMulti()) {
                    return true;
                }
//                return true;
            }
        }
    
    else if(arr.get (i) 
        .cls_pt.equalsIgnoreCase("$"))
        {
            return true;
    }


return false;
    }

    boolean clas_m() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("class")) {
            i++;
            if (z()) {
              //  i++;
                if (arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
                    i++;
                    if (arr.get(i).cls_pt.equalsIgnoreCase("{")) {
                      i++;
                        if (classbdy()) {
                          //  i++;
                            if (main_st()) {
                                i++;
                                if (classbdy()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean clasMulti() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("class")) {
            if (classM2()) {
                i++;
                if (clasMulti()) {
                    return true;
                }
            }
        } else if (arr.get(i).cls_pt.equalsIgnoreCase("}")||arr.get(i).val_pt.equalsIgnoreCase("$")) {
            return true;
        }
        return false;
    }
    
    boolean classM2()
    {      
         if (arr.get(i).cls_pt.equalsIgnoreCase("class")) {
           i++;
             if (z()) {
                //i++;
                if (arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
                    i++;
                    if (arr.get(i).cls_pt.equalsIgnoreCase("{")) {
                        i++;
                        if (classbdyM()) {
                            ///i++;
                            if (arr.get(i).cls_pt.equalsIgnoreCase("}")||arr.get(i).val_pt.equalsIgnoreCase("$")) {
                                //i++;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean classbdyM() {
        if(arr.get(i).cls_pt.equalsIgnoreCase("ID") && arr.get(i+1).cls_pt.equalsIgnoreCase("ID"))
        {
            if(object())
              {
                  i++;
                  if(classbdyM())
                  {
                  //    i++;
                  return true;
                  }
              }
          }
        else if(arr.get(i).cls_pt.equalsIgnoreCase("ID")&& !arr.get(i).val_pt.equalsIgnoreCase("main")||arr.get(i).cls_pt.equalsIgnoreCase("DT")  )
        {
            if(dec())
              {
                  if (classbdyM()) {
//                    i++;
                    return true;
                }
            }
        }
        else if(arr.get(i).val_pt.equalsIgnoreCase("create"))
        {
            if(fn_create()) {
               i++;
                if (classbdyM()) {
//                    i++;
                    return true;
                }

            }
        }
         else if (arr.get(i).cls_pt.equalsIgnoreCase("}")
                 ||arr.get(i).val_pt.equalsIgnoreCase("$"))
                 //||arr.get(i).val_pt.equalsIgnoreCase("main")) 
         {
            return true;
        }
     
        return false;
    }
    
    boolean main_st() {
        if (arr.get(i).val_pt.equalsIgnoreCase("main")) {
            i++;
            if (arr.get(i).cls_pt.equalsIgnoreCase("(")) {
                i++;
                if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
                    i++;
                    if (arr.get(i).cls_pt.equalsIgnoreCase("{")) {
                        i++;
                        if (main_body()) {
                            //i++;
                            if (arr.get(i).cls_pt.equalsIgnoreCase("}")) {
                                return true;
                            }
                        }
                    }     
                }
            }
        }

        return false;
    }

        boolean OEE() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("!")) {
            i++;
            if (F()) {
                if (TDASH()) {

                    if (EDASH()) {

                        if (REDASH()) {

                            if (AEDASH()) {

                                if (OEDASH()) {
                                    return true;
                                }
                                return true;
                            }
                            return true;
                        }
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        } else if (CONST()) {
            if (TDASH()) {

                if (EDASH()) {

                    if (REDASH()) {

                        if (AEDASH()) {

                            if (OEDASH()) {

                                return true;
                            }
                            return true;
                        }
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        } else if (FID()) {
            if (TDASH()) {
                if (EDASH()) {

                    if (REDASH()) {
                        if (AEDASH()) {

                            if (OEDASH()) {
                                return true;
                            }
                            return true;
                        }
                        return true;
                    }
                    return true;
                }
                return true;
                //}
                //  return true;

            }
            return false;
        } else if (arr.get(i).cls_pt.equalsIgnoreCase("(")) {
            i++;
            if (OEE()) {

                if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {

                    i++;
                    if (TDASH()) {

                        if (EDASH()) {

                            if (REDASH()) {

                                if (AEDASH()) {

                                    if (OEDASH()) {
                                        return true;
                                    }
                                    return true;
                                }
                                return true;
                            }
                            return true;
                        }
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        } else if (FOLLOWOFOEE()) {
            return true;
        }
        return false;
    }

    boolean FOLLOWOFOEE() {
        if (arr.get(i).cls_pt.equalsIgnoreCase(")") 
                || arr.get(i).val_pt.equalsIgnoreCase(",")
                ||arr.get(i).val_pt.equalsIgnoreCase("$")
                || arr.get(i).cls_pt.equalsIgnoreCase("]")) {
            return true;
        }
        return false;
    }

    boolean OEDASH() {

        if (arr.get(i).val_pt.equalsIgnoreCase("||")) {
            i++;
            if (AE()) {
                if (OEDASH()) {
                    return true;
                }
                return true;
            }
            return true;

        } else if (arr.get(i).cls_pt.equalsIgnoreCase(")") 
                || arr.get(i).val_pt.equalsIgnoreCase("$") 
                ||arr.get(i).val_pt.equalsIgnoreCase("$")
                || FOLLOWOFOEE()) {
            return true;
        }
        return false;
    }

    boolean AE() {

        if (arr.get(i).cls_pt.equalsIgnoreCase("!")) {
            i++;
            if (F()) {
                if (TDASH()) {

                    if (EDASH()) {

                        if (REDASH()) {

                            if (AEDASH()) {
                                return true;
                            }
                            return true;
                        }
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        } else if (CONST()) {
            if (TDASH()) {

                if (EDASH()) {

                    if (REDASH()) {

                        if (AEDASH()) {

                            return true;
                        }
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        } else if (FID()) {
            if (TDASH()) {
                //  System.out.print("TDASH(");
                if (EDASH()) {

                    if (REDASH()) {

                        if (AEDASH()) {

                            return true;
                        }
                        return true;
                    }
                    return true;
                }
                return true;
                //}
                //return true;

            }
            return false;
        } else if (arr.get(i).cls_pt.equalsIgnoreCase("(")) {
            i++;
            if (OEE()) {
                if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
                    i++;
                    if (TDASH()) {

                        if (EDASH()) {

                            if (REDASH()) {

                                if (AEDASH()) {

                                    return true;
                                }
                                return true;
                            }
                            return true;
                        }
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return false;
    }

    boolean AEDASH() {

        if (arr.get(i).val_pt.equalsIgnoreCase("&&")) {
            i++;
            if (RE()) {
                if (AEDASH()) {
                    return true;
                }
                return true;
            }
            return true;
        } else if (arr.get(i).val_pt.equalsIgnoreCase("||") 
                || arr.get(i).cls_pt.equalsIgnoreCase(")") 
                ||arr.get(i).val_pt.equalsIgnoreCase("$")) {
            return true;
        }
        return false;
    }

    boolean RE() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("!")) {
            i++;
            if (F()) {
                if (TDASH()) {

                    if (EDASH()) {

                        if (REDASH()) {

                            return true;
                        }
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        } else if (CONST()) {
            if (TDASH()) {

                if (EDASH()) {

                    if (REDASH()) {

                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        } else if (arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
            i++;
            //if (FN_CALL()) {
            if (TDASH()) {

                if (EDASH()) {

                    if (REDASH()) {
                        return true;
                    }
                    return true;
                }
                return true;
            }
            //  return true;

            //}
            return false;
        } else if (arr.get(i).cls_pt.equalsIgnoreCase("(")) {
            i++;
            if (OEE()) {
                if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
                    i++;
                    if (TDASH()) {

                        if (EDASH()) {

                            if (REDASH()) {
                                //    return true;

                                return true;
                            }
                            return true;
                        }
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return false;
    }

    boolean REDASH() {

        if (p.ROP(arr.get(i).val_pt)) {
            i++;
            if (E()) {
                if (REDASH()) {
                    return true;
                }
                return true;
            }
            return true;
        } else if (arr.get(i).val_pt.equalsIgnoreCase("&&") 
                || arr.get(i).val_pt.equalsIgnoreCase("||") 
                || arr.get(i).cls_pt.equalsIgnoreCase(")")
                ||arr.get(i).cls_pt.equalsIgnoreCase(";")) {
            return true;

        }
        return false;
    }

    boolean E() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("!")) {
            i++;
            if (F()) {
                if (TDASH()) {

                    if (EDASH()) {

                        return true;
                    }
                    return true;
                }
                return true;
            }

        } else if (CONST()) {
            if (TDASH()) {

                if (EDASH()) {

                    return true;
                }
                return true;
            }
            return true;

        } else if (FID()) {
            if (TDASH()) {

                if (EDASH()) {

                    return true;
                }
                return true;
                //    }
                //  return true;

            }
            return false;
        } else if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
            i++;
            if (OEE()) {
                if (arr.get(i).cls_pt.equalsIgnoreCase("(")) {
                    i++;
                    if (TDASH()) {

                        if (EDASH()) {

                            return true;
                        }
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return false;
    }

    boolean EDASH() {

        if (p.MDM(arr.get(i).val_pt) ||p.PM(arr.get(i).val_pt)) { //p.MDM(arr.get(i).val_pt) ||p.PM(arr.get(i).val_pt)
            i++;
            if (T()) {
                if (EDASH()) {
                    return true;
                }
                return true;
            }
            return true;
        } else if (p.ROP(arr.get(i).val_pt)
                || arr.get(i).val_pt.equalsIgnoreCase("&&")
                ||arr.get(i).val_pt.equalsIgnoreCase("||")
                || arr.get(i).cls_pt.equalsIgnoreCase(")") 
                ||arr.get(i).val_pt.equalsIgnoreCase("$")
                ||arr.get(i).cls_pt.equalsIgnoreCase(")")) {

            return true;
        }
        return false;
    }

    boolean T() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("!")) {
            i++;
            if (F()) {
                if (TDASH()) {
                    return true;
                }
                return true;
            }
            return true;
        } else if (CONST()) {
            if (TDASH()) {

                return true;
            }
            return true;

        } else if (FID()) {
            if (TDASH()) {
                return true;
            }
            return true;

            //}
            //return true;
        } else if (arr.get(i).cls_pt.equalsIgnoreCase("(")) {
            i++;
            if (OEE()) {
                if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
                    i++;
                    if (TDASH()) {

                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return false;
    }

    boolean TDASH() {

        if (p.MDM(arr.get(i).val_pt)) { // i =3
            i++;

            if (F()) {
                if (TDASH()) {
                    return true;
                }
                return true;
            }
            return true;
        } else if (p.MDM(arr.get(i).val_pt) ||p.PM(arr.get(i).val_pt) 
                || p.ROP(arr.get(i).val_pt) 
                        || arr.get(i).cls_pt.equalsIgnoreCase("&&") 
                                || arr.get(i).cls_pt.equalsIgnoreCase("||")
                ||arr.get(i).cls_pt.equalsIgnoreCase("!")
                                        ||arr.get(i).val_pt.equalsIgnoreCase("$")
                                                || arr.get(i).cls_pt.equalsIgnoreCase(")")) {

            return true;
        }
        return false;
    }

    boolean F() {

        if (CONST()) {
            return true;
        } else if (arr.get(i).cls_pt.equalsIgnoreCase("(")) {
            i++;
            if (OEE()) {
                if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
                    i++;
                    return true;
                }
                return true;
            } else if (FID()) {
                return true;
            }
            return true;
        } else if (FDASH()) {
            return true;
        }
        return false;
    }

    boolean FID() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
            i++;
            if (IDDASH()) {
                return true;
            }
            return true;
        }
        return false;
    }

    boolean IDDASH()
    {
    if(Functioncalling())
    {
    return true; 
    }
    else if (p.MDM(arr.get(i).val_pt)|| p.MDM(arr.get(i).val_pt) 
            ||p.PM(arr.get(i).val_pt) || p.ROP(arr.get(i).val_pt)
                || arr.get(i).val_pt.equalsIgnoreCase("&&") 
                        || arr.get(i).val_pt.equalsIgnoreCase("||")
                                || arr.get(i).cls_pt.equalsIgnoreCase(")"))
    {
    return true;
    }
    return false;
    }

    boolean Functioncalling() {
        if (FUNC()) {
            return true;

        } 
        return false;
    }    

    boolean FDASH() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("!")) {
            i++;
            if (FDASH()) {
                return true;
            }
            return true;
        } else if ((p.MDM(arr.get(i).val_pt)) 
                || (p.MDM(arr.get(i).val_pt) 
                ||p.PM(arr.get(i).val_pt))
                || (p.ROP(arr.get(i).val_pt))
                || (arr.get(i).val_pt.equalsIgnoreCase("&&")) 
                || (arr.get(i).val_pt.equalsIgnoreCase("||")) 
                ||(arr.get(i).cls_pt.equalsIgnoreCase(")"))) {
            return true;
        }

        return false;
    }

    boolean CONST() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("int_const")) {
            i++;
            //System.out.print ("are we here");
            return true;
        } else if (arr.get(i).cls_pt.equalsIgnoreCase("float_const")) {
            i++;
            return true;
        } else if (arr.get(i).cls_pt.equalsIgnoreCase("char_const")) {

            i++;
            return true;
        } else if (arr.get(i).cls_pt.equalsIgnoreCase("String_const")) {
            i++;
            return true;

        } //else if ("IDENTIFIER"arr.get(i).cls_pt.equalsIgnoreCase("")) {
        //i++;
        //return true;
        //}

        return false;
    }

    boolean FUNC() {
        if (arr.get(i).val_pt.equalsIgnoreCase(".")) {

            i++;

            if (arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
                i++;

                if (FUNC()) {
                    return true;
                }
                return true;
            }
            return true;
        } else if (CALLING()) {
            return true;
        } else if (FUNCDASH()) {
            return true;
        }
        return false;
    }

    boolean CALLING() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("(")) {
            i++;
            if (PARAMETER()) {
                if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
                    i++;
                    if (CALLINGDASH()) {
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return false;
    }

    boolean FUNCDASH() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("(") && arr.get(i).cls_pt.equalsIgnoreCase(")")) {
            i++;
            return true;
        }
        return false;
    }

    boolean PARAMETER() {
        if (OEE()) {
            if (x()) {
                return true;
            }
            return true;
        } else if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
            return true;
        }

        return false;
    }

    boolean x() {
        if (arr.get(i).val_pt.equalsIgnoreCase(",")) {
            i++;
            if (OEE()) {
                if (x()) {
                    return true;
                }
                return true;
            }
            return true;
        } else if (CALLINGDASH()) {
            return true;
        } else if (Arr()) {
            return true;
        } else if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
            return true;
        }
        return false;
    }

    boolean CALLINGDASH() {
//        if (i < tok.size()) {
            if (arr.get(i).val_pt.equalsIgnoreCase(".")) {
                i++;
                if (arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
                    i++;
                    if (Functioncalling()) {
                        return true;
                    }
                    return true;
                }
                return true;
            } else if (arr.get(i).cls_pt.equalsIgnoreCase(")")
                    || arr.get(i).val_pt.equalsIgnoreCase("$") ) {
                return true;
            }
            return false;
        //}
        //return false;
    }

    boolean Arr() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("[")) {
            i++;
            if (OEE()) {
                if (arr.get(i).cls_pt.equalsIgnoreCase("]")) {
                    i++;
                    if (ArrDash()) {
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return false;
    }

    boolean ArrDash() {
        if (arr.get(i).val_pt.equalsIgnoreCase(".")) {
            i++;
            if (arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
                i++;
                if (Functioncalling()) {
                    return true;
                }
                return true;
            }
            return true;
        } else if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
            return true;
        }
        return false;
    }

  boolean ff1() {
//      if(arr.get(i).cls_pt.equalsIgnoreCase("ID")||arr.get(i).val_pt.equalsIgnoreCase(".")){
//          i++;
      
        if (arr.get(i).val_pt.equalsIgnoreCase(".")
                ||arr.get(i).val_pt.equalsIgnoreCase(".")&& !arr.get(i+1).val_pt.equalsIgnoreCase("[")) {
            if (arr.get(i).val_pt.equalsIgnoreCase(".")) {
                i++;
                if (ff2()) {
                    return true;
                }
            }
            } else if (arr.get(i).val_pt.equalsIgnoreCase("[")) {
               // i++;
                if (ARR()) {
                    if (ff1()) {
                        return true;
                    }
                }
            }
        
//      }
       else if (//arr.get(i).val_pt.equalsIgnoreCase("&&")
            //    || arr.get(i).val_pt.equalsIgnoreCase("||") 
          //      || p.ROP(arr.get(i).val_pt) 
                // p.MDM(arr.get(i).val_pt))// ||arr.get(i).val_pt.equalsIgnoreCase("$"))
        //        || p.PM(arr.get(i).val_pt))
        arr.get(i).val_pt.equalsIgnoreCase("&&")
                        ||arr.get(i).val_pt.equalsIgnoreCase("||")
//                        ||arr.get(i).val_pt.equalsIgnoreCase("}")
                || arr.get(i).val_pt.equalsIgnoreCase("$")        
                ||arr.get(i).val_pt.equalsIgnoreCase(")")
                        //||arr.get(i).val_pt.equalsIgnoreCase("]")
                        ||p.ROP(arr.get(i).val_pt)
                        ||p.MDM(arr.get(i).val_pt)
                        ||arr.get(i).val_pt.equalsIgnoreCase("+")
                        ||arr.get(i).val_pt.equalsIgnoreCase("-") )
                       
                {
            return true;
        }
        return false;
    }
  
boolean ff2() {
        if (arr.get(i).val_pt.equalsIgnoreCase("ID")) {
            i++;
                if (ff1()) {
                    return true;
                }
        }else if (fn_call()) {
                if (ff1()) {
                  return true;  
                }
            }
        
        return false;
    }
  
    boolean fn_call() {
        if (arr.get(i).val_pt.equalsIgnoreCase("fn")) {
            i++;
            if (arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
                i++;
                if (arr.get(i).cls_pt.equalsIgnoreCase("(")) {
                    i++;
                    if (PA()) {
                        
                        if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

boolean PA()
{
    if(OEE())
    {
        if(R())
        {
        //    i++;
            return true;
        }
            
    }
    else if(arr.get(i).cls_pt.equalsIgnoreCase(")"))
    {
        return true;
    }
    return false;
}

boolean R()
{
    if(arr.get(i).val_pt.equalsIgnoreCase(","))
    {i++;
    if(OEE())
    {
        if(R())
        {
            //i++;
            return true;
        }
    }
        
    }
    else if(arr.get(i).val_pt.equalsIgnoreCase(")"))
    {
        return true;
    }
    return false;
}
boolean ARR()
{
    if(arr.get(i).cls_pt.equalsIgnoreCase("["))
    {i++;
        if(index())
        {
            if(arr.get(i).cls_pt.equalsIgnoreCase("]"))
            {
                return true;
            }
        }
    }
     
    return false;
}
boolean index()
{
    if(OEE())
    {
        return true;
    }
    else if(arr.get(i).cls_pt.equalsIgnoreCase("]"))
    {
        return true;
    }
    return false;
}
boolean curl()
{
    if(OEE())
    {
        return true;
    }
    else if(arr.get(i).cls_pt.equalsIgnoreCase("}"))
    {
        return true;
    }
    return false;
}
boolean round()
{
     if(OEE())
    {
        return true;
    }
    else if(arr.get(i).cls_pt.equalsIgnoreCase(")"))
    {
        return true;
    }
    return false;
}
boolean for_st() {
        if (arr.get(i).cls_pt.equalsIgnoreCase("for")) {
            i++;
            if (arr.get(i).cls_pt.equalsIgnoreCase("ID")) {
                i++;
                if (arr.get(i).cls_pt.equalsIgnoreCase("in")) {
                    i++;
                    if (arr.get(i).val_pt.equalsIgnoreCase("range")) {
                        i++;
                        if (arr.get(i).cls_pt.equalsIgnoreCase("(")) {
                            i++;
                            if (RR()) {
                              // i++;
                                if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
                                    i++;
//                                    if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
//                                        i++;
                                        if (arr.get(i).cls_pt.equalsIgnoreCase("{")) {
                                            i++;
                                            if (BODY()) {
                                                if (arr.get(i).cls_pt.equalsIgnoreCase("}")) {
                                                    return true;
                                                }
                                            }

                                        }
                                    }
                                }
                                //else if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
//                                    i++;
//                                    if (arr.get(i).cls_pt.equalsIgnoreCase("{")) {
//                                        i++;
//                                        if (BODY()) {
//                                            if (arr.get(i).cls_pt.equalsIgnoreCase("}")) {
//                                                return true;
//                                            }
//                                        }

//                                    }
//                                }
                            }
                        }
                    }
                }
            }
        else if(arr.get(i).cls_pt.equalsIgnoreCase("}")
            ||arr.get(i).cls_pt.equalsIgnoreCase("DT")
            || arr.get(i).cls_pt.equalsIgnoreCase("ID")
            ||arr.get(i).cls_pt.equalsIgnoreCase("ID") && arr.get(i+1).cls_pt.equalsIgnoreCase("ID")
            ||arr.get(i).cls_pt.equalsIgnoreCase("for")
            ||arr.get(i).cls_pt.equalsIgnoreCase("class")
            ||arr.get(i).cls_pt.equalsIgnoreCase("while")
                ||arr.get(i).cls_pt.equalsIgnoreCase("if")
                ||arr.get(i).cls_pt.equalsIgnoreCase("fn")
                )
    {
        return true;
    }
             
        return false;
    }

 //    }

     boolean RR() {
            if (ID_Const()) {
                i++;
                if (X2()) {
                    //i++;
                    return true;
                }
            }
        return false;
    }

     boolean ID_Const() {

        if (arr.get(i).cls_pt.equalsIgnoreCase("ID") || arr.get(i).cls_pt.equalsIgnoreCase("int_const")) {
            return true;
        } 
        return false;
    }

     boolean X2() {
        if (arr.get(i).val_pt.equalsIgnoreCase(",")) {
            if (arr.get(i).val_pt.equalsIgnoreCase(",")) {
                i++;
                if (ID_Const()) {
                    i++;
                    if (X3()) {
                        return true;
                    }
                }

            }
        } else if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
            return true;
        }
        return false;
    }

     boolean X3() {
        if (arr.get(i).val_pt.equalsIgnoreCase(",")) {
            if (arr.get(i).val_pt.equalsIgnoreCase(",")) {
                i++;
                if (ID_Const()) {
                   i++;
                    return true;
                }
            }
        } else if (arr.get(i).cls_pt.equalsIgnoreCase(")")) {
            return true;
        }

        return false;
    }

}






