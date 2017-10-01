/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.infixtopostfix;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CURRENT IMPLEMENTATION: - Assumes single digit numbers and tokens - Assumes
 * single character tokens
 *
 * TODO: - add a scanner to take in multi digit numbers and tokens
 *
 * @author ken
 */
public class InfixToPostfix {

    NumberParser numberParser = new NumberParser();
    PostfixModel postfixModel;
    //StringBuilder output = new StringBuilder();
    //LinkedList<Opperator> stack = new LinkedList();

    public PostfixModel transform(String input) {
        postfixModel = new PostfixModel();
        //StringBuilder output = postfixModel.getOutput();
        LinkedList<Token> output = postfixModel.getOutput();
        LinkedList<Opperator> stack = postfixModel.getStack();
//SOURCE: http://scriptasylum.com/tutorials/infix_postfix/algorithms/infix-postfix/index.htm       
//    Scan the Infix string from left to right.

        top:
        for (int i = 0; i < input.length(); i++) {
            //System.out.print(postfixModel.showState());//For Debug ONLY
            Character cur = input.charAt(i);
            //GLOB the number
            if (numberParser.isParsable(cur.toString())) {  //if this is a number
                Integer value = numberParser.getNumber();
                int j;
                for(j = i + 1; j < input.length() && numberParser.isParsable(((Character)input.charAt(j)).toString()); j++){
                        value *= 10;
                        value += numberParser.getNumber();
                }
                i = j - 1;
                //NUMBER 
                Opperand opperand = new Opperand(); //TODO: This needs to take in multiple numbers, ie: advance until not a digit
                opperand.setValue(value.floatValue());
                output.add(opperand);
                continue;
                //cur = input.charAt(++i);
            }
            //not a number, is an operator, or bracket
            try {
                if (cur == ')') {
                    //System.out.println("here");
                    while (stack.size() > 0) {
                        //System.out.println("here2");
                        Opperator untilStart = stack.pop();
                        //System.out.println("untilStart: " + untilStart.symbol);
                        if (untilStart.getSymbol().equals("(")) {
                            //System.out.println("here3");
                            continue top;
                        } else {
                            //System.out.println("here4");
                            //output.append(untilStart.symbol);
                            Opperator opperator = new Opperator(untilStart.getSymbol());
                            output.add(opperator);
                        }
                    }
                }
                //Opperator opp = new Opperator();
                Opperator opp = new Opperator(cur); //Throws MalformedExpression
                if (opp.getSymbol().equals("(")) {
                    stack.push(opp);
                    continue top;
                }
                while (stack.size() > 0 && stack.getFirst().precidence >= opp.precidence && (stack.peek().getSymbol().equals("(") == false)) {
                    Opperator opperator = stack.pop();//new Opperator(stack.pop().getSymbol());
                    output.add(opperator);
                    //output.append(stack.pop().symbol);
                }
                stack.push(opp);
            } catch (MalformedExpression ex) {
                Logger.getLogger(InfixToPostfix.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //System.out.println("Finishing");
        while (stack.size() > 0) {
            Opperator opp = stack.pop();
            //System.out.println("final: " + opp.getSymbol());
            //output.append(opp.symbol);
            //Opperator opperator = Opperator.isOpperator(opp.symbol);
            output.add(opp);
        }

        return postfixModel;
    }
}

class NumberParser {

    Integer number;

    boolean isParsable(String input) {
        number = null;
        try {
            number = Integer.valueOf(input, 10);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    Integer getNumber() {
        return number;
    }
}

class MalformedExpression extends Exception {

    public MalformedExpression() {
        super("The expression is malformed - and I'm not going to help fix it =)");
    }
}
