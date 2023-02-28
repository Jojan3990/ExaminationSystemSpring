package com.rightfindpro.become.testIntern;

import java.util.Scanner;
import java.util.Stack;

//Check for Balanced Brackets in an expression (well-formedness) using Stack
public class ExpressionCheck {

    boolean checkExpressionBal(String str){
        Scanner sc=new Scanner(System.in);
        Stack<Character> stk=new Stack<>();
        Character chCheck;

        if(str.length()%2!=0){
            return false;
        }
        else {
            for(int i=0;i<str.length();i++){
                Character c=str.charAt(i);
                if(c=='[' || c=='(' || c=='{'){
                    stk.push(c);
                    System.out.println(stk);
                    continue;
                }
                if(stk.isEmpty()){
                    return true;
                }

                switch (c){
                    case ']':
                        chCheck=stk.pop();
                        if(chCheck!='['){
                            return false;
                        }
                        else {
                            break;
                        }
                    case ')':
                        chCheck=stk.pop();
                        if(chCheck!='('){
//                        System.out.println("This is executed");
                            return false;
                        }
                        else{
                            break;
                        }
                    case '}':
                        chCheck=stk.pop();
                        if(chCheck!='{'){
                            return false;
                        }
                        else{
                            break;
                        }
                }
            }
        }


//        if(stk.isEmpty()){
//            return true;
//        }
        System.out.println(stk.isEmpty());
        return stk.isEmpty();
    }
    public static void main(String[] args) {


        //pushing expression to stack
        ExpressionCheck ex=new ExpressionCheck();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter expression");
        String str=sc.next();
        boolean finalVal=ex.checkExpressionBal(str);
//        System.out.println(finalVal);
        if(finalVal==true){
            System.out.println("balanced");
        }
        else{
            System.out.println("Unbalanced");
        }

    }
}



