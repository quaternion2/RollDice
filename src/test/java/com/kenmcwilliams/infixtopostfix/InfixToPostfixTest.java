/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.infixtopostfix;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ken
 */
public class InfixToPostfixTest {
    InfixToPostfix iftopf;//= new InfixToPostfix();
    //EvalPostfix eval;
    
    public InfixToPostfixTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.iftopf = new InfixToPostfix();
        //this.eval = new EvalPostfix();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of transform method, of class InfixToPostfix.
     */
    @org.junit.Test
    public void testTransform() {
        System.out.println("transform");
        //TODO: Fix the following!
        outputTemplate("8/2/2/2", "82/2/2/");
        outputTemplate("8+2-3+4", "82+3-4+");
        outputTemplate("8-2+3-4", "82-3+4-");
        
        outputTemplate("2*3-4/5", "23*45/-");
        outputTemplate("2*(3+4)*5", "234+*5*");
        outputTemplate("2+3*4+5", "234*+5+");
        outputTemplate("(2+3)*(4+5)", "23+45+*");
        outputTemplate("2*3+4*5", "23*45*+");
        outputTemplate("2+3+4+5", "23+4+5+");
        outputTemplate("3d6", "36d");
        outputTemplate("4d6l1", "46d1l");
        outputTemplate("4d6h1", "46d1h");
        
        outputTemplate("2*3-4/5*9", "23*45/9*-"); //THIS TEST CASE IS BROKEN! DIVISION IS CURRENTLY HAPPENING AFTER MULT! (IN THIS CASE)
        //assertTrue(this.transform("2*3-4/5").equalsIgnoreCase("23*45/-"));
        //assertTrue(this.transform("2*(3+4)*5").equalsIgnoreCase("234+*5*"));
    }

    private void outputTemplate(String input, String expected) {
        System.out.println("input: " + input);
        PostfixModel model = this.iftopf.transform(input);
        String transform = model.toString();
        System.out.println("is: " + transform);
        System.out.println("be: " + expected);
        //System.out.println("total: " + eval.eval(model));
        String result = transform.equalsIgnoreCase(expected) ? "GOOD" : "BAD";
        System.out.println(result);
        if (result.equalsIgnoreCase("BAD")){
            assertTrue(false);
        }
        System.out.println("------------");
    }

    public PostfixModel transform(String input) {
        InfixToPostfix instance = new InfixToPostfix();
        return instance.transform(input);
    }

}
