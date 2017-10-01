/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.infixtopostfix;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author ken
 */
public class EvalPostfix {

    private PostfixModel model = new PostfixModel();
    //private Stack<Opperand> opperands = new LinkedList();
    Deque<Opperand> opperands = new ArrayDeque();

    /**
     *
     * @return @throws Exception
     */
    private String eval() throws Exception {
        LinkedList<Token> output = model.getOutput();
        for (Token t : output) {

            if (t instanceof Opperand) {
                opperands.push((Opperand) t);
            } else if (t instanceof Opperator) {
                Opperator opp = (Opperator) t;
                //All these opperators
                Opperand b;// = opperands.pop();
                Opperand a;// = opperands.pop();
                b = opperands.pop();
                a = opperands.pop();
                switch (opp.getSymbol()) {
                    case "+":
                        float sum = a.getValue() + b.getValue();
                        opperands.push(new Opperand(sum));
                        break;
                    case "-":
                        float diff = a.getValue() - b.getValue();
                        opperands.push(new Opperand(diff));
                        break;
                    case "*":
                        float factor = a.getValue() * b.getValue();
                        opperands.push(new Opperand(factor));
                        break;
                    case "/":
                        System.out.println("Dividing: a/b where a(" + a.getValue() + ") b(" + b.getValue() + ") ");
                        float divisor = a.getValue() / b.getValue();//double check this
                        opperands.push(new Opperand(divisor));
                        break;
                    case "d":
                        //float diceTotal = 0;
                        List<Float> values = new ArrayList();
                        for (int i = 0; i < a.getValue(); i++) {
                            values.add(getRandom(b.getValue()));
                        }
                        values.sort(Comparator.naturalOrder());
                        Opperand oppValues = new Opperand(values);
                        System.out.println(oppValues);
                        opperands.push(oppValues);
                        break;
                    case "l":
                        if (a.getType() != Opperand.Type.VECTOR) {
                            throw new Exception("Opperator 'l' only works after a 'd' opperator.");
                        } else { //good
                            List<Float> list = a.getValues();
                            int nToRemove = Math.round(b.getValue());
                            this.removeFirstX(list, nToRemove);
                            opperands.push(new Opperand(list));
                            //c.compareTo(factor)
                        }
                        break;
                    case "h":
                        if (a.getType() != Opperand.Type.VECTOR) {
                            throw new Exception("Opperator 'h' only works after a 'd' opperator.");
                        } else {//good
                            List<Float> list = a.getValues();
                            int nToRemove = Math.round(b.getValue());
                            this.removeLastX(list, nToRemove);
                            opperands.push(new Opperand(list));
                        }
                        break;
                }
            }
        }
        NumberFormat nf = new DecimalFormat("0.###");
        String formated = nf.format(opperands.pop().getValue());
        return formated;
    }

    public Float getRandom(float max) {
        max = max - 1;
        long maxRounded = Math.round(max);
        Double result;//1 to max
        result = (Math.random() * max + 1);
        Long rounded = Math.round(result);
        return rounded.floatValue();
    }

    public String eval(PostfixModel model) throws Exception {
        this.model = model;
        return this.eval();
    }

//    public String eval() {
//        return this.eval(this.model);
//    }
    //NOTE: LIST MUST BE SORTED IN NATURAL ORDER!
    private List<Float> removeLastX(List<Float> list, int nToRemove) throws Exception {
        Collections.reverse(list);
        this.removeFirstX(list, nToRemove);
        return list;
    }

    //NOTE: LIST MUST BE SORTED IN NATURAL ORDER!
    private List<Float> removeFirstX(List<Float> list, int nToRemove) throws Exception {
        //Collections.reverse(list);
        if (list.size() == nToRemove) {
            throw new Exception("Removing All the dice from the set seems silly");
        } else if (list.size() == nToRemove) {
            throw new Exception("Attempt to remove more dice than in the set!");
        }
        ListIterator<Float> itter = list.listIterator();
        itter.next();
        for (int i = 0; i < nToRemove; i++) {
            itter.remove();
            if (itter.hasNext()) {
                itter.next();
            }
        }
        return list;
    }
}
