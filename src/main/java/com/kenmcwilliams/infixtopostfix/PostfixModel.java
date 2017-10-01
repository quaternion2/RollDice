/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.infixtopostfix;

import java.util.LinkedList;

/**
 *
 * @author ken
 */
public class PostfixModel {
    private LinkedList<Token> output = new LinkedList();
    private LinkedList<Opperator> stack = new LinkedList();

    /**
     * @return the output
     */
    public LinkedList<Token> getOutput() {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(LinkedList<Token> output) {
        this.output = output;
    }

    /**
     * @return the stack
     */
    public LinkedList<Opperator> getStack() {
        return stack;
    }

    /**
     * @param stack the stack to set
     */
    public void setStack(LinkedList<Opperator> stack) {
        this.stack = stack;
    }
    
    @Override
    public String toString(){
        //System.out.println("PostfixModel::toString");
        StringBuilder builder = new StringBuilder();
        for(Token token: output){
            //System.out.println("PostfixModel: " + token.getSymbol());
            builder.append(token.getSymbol());
        }
        return builder.toString();
    }
    
    public String showState(){
        return "output: " + this.toString() + "\n" + 
        "stack: " + stack.toString() + "\n";
    }
}
