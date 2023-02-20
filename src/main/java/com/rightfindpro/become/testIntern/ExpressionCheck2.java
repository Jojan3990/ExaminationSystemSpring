package com.rightfindpro.become.testIntern;

import java.util.Scanner;
import java.util.Stack;

public class ExpressionCheck2 {

    boolean checkExpression(String str){
        Stack stk=new Stack();
        Character ch;
        if(str.length()%2!=0){
            return false;
        }
        else {
            for (int i=0;i<str.length();i++){
                stk.push(str.charAt(i));
            }
            for (int i=0;i<stk.size();i++){
                ch= (Character) stk.get(i);
                if(ch==stk.size()-1){

                }
            }

        }
        return true;
    }
    public static void main(String[] args) {
        ExpressionCheck2 expressionCheck=new ExpressionCheck2();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Expression here");
        String ex=sc.next();
        boolean valCheck=expressionCheck.checkExpression(ex);
        if(valCheck==true){
            System.out.println("balanced");
        }
        else{
            System.out.println("Unbalanced");
        }
    }
}
