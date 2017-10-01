/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.infixtopostfix;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class determines if there are certain prefixes ie: "2x", which when
 * appended to the string will cause the opperations to be produced that many
 * times
 *
 * @author ken
 */
public class StringExecuter {

    InfixToPostfix ifToPf = new InfixToPostfix();
    EvalPostfix eval = new EvalPostfix();

    void execute(String input) throws Exception {
        String regex = "^(\\d+)x.*"; //must start with a number followed by an x and possibly other content
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        //input = input.replaceFirst("^([d]+)x", "");
        
        int timesToCall = 1;
        if (m.matches()) {
            String group = m.group(1);
            String toRemove = group + "x";
            //System.out.println("removing: " + toRemove);
            input = input.replaceFirst(toRemove, "");
           
            //System.out.println("group1: " + group);
            timesToCall = Integer.valueOf(group, 10);
        }
        for (int i = 0; i < timesToCall; i++) {
            //System.out.println("working with: " + input);
            PostfixModel model = ifToPf.transform(input);
            //String transform = model.toString();
            //String transform = model.toString();
            //System.out.println(transform);
            System.out.print(eval.eval(model));
            if(i < timesToCall - 1){
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
