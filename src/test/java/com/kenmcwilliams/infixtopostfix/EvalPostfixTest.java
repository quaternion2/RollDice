/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.infixtopostfix;

import java.util.ArrayList;
import java.util.List;
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
public class EvalPostfixTest {

    List<PostfixModel> basicAlgebra;
    List<PostfixModel> listAlgebra;
    List<String> basic_expected;

    public EvalPostfixTest() {
        basicAlgebra = new ArrayList();
        basic_expected = new ArrayList();
        InfixToPostfix ifToPf = new InfixToPostfix(); //Warning: This is an actual impl, should be stubed 
        basicAlgebra.add(ifToPf.transform("2*3-4/5"));
        basic_expected.add("5.2");
        basicAlgebra.add(ifToPf.transform("2*(3+4)*5"));
        basic_expected.add("70");
        basicAlgebra.add(ifToPf.transform("2+3*4+5"));
        basic_expected.add("19");
        basicAlgebra.add(ifToPf.transform("(2+3)*(4+5)"));
        basic_expected.add("45");
        basicAlgebra.add(ifToPf.transform("2*3+4*5"));
        basic_expected.add("26");
        basicAlgebra.add(ifToPf.transform("2+3+4+5"));
        basic_expected.add("14");

        //basicAlgebra.add(ifToPf.transform("2*3-4/5*100"));
        //basic_expected.add("-74");
//THIS PART DOWN NEED MORE THINKING
        //listAlgebra.add(ifToPf.transform("3d6"));//should be able to extract the first list element...
        //need to verify 
        //expected.add("???");
        //listAlgebra.add(ifToPf.transform("4d6l1"));
        //expected.add("???");
        //listAlgebra.add(ifToPf.transform("4d6h1"));
        //expected.add("???");
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        //this.model = new PostfixModel();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of eval method, of class EvalPostfix.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testEval() throws Exception {
        System.out.println("eval");
        EvalPostfix instance = new EvalPostfix();
        for (int i = 0; i < basicAlgebra.size(); i++) {
            String result = instance.eval(basicAlgebra.get(i));
            String expect = basic_expected.get(i);
            System.out.println("expect: " + expect);
            System.out.println("result: " + result);
            assertTrue(result.equals(expect));
        }
        //String expResult = "";
        //String result = instance.eval();
        //assertEquals(expResult, result);
    }

    /**
     * Test of getRandom method, of class EvalPostfix.
     */
    @Test
    public void testGetRandom() {
        System.out.println("getRandom");
        //do 100 itterations, no result should be less than 1 and no result should be greater than 6
        for (int i = 0; i < 100; i++) {
            float max = 6.0F;
            EvalPostfix instance = new EvalPostfix();
            //Float expResult = null;
            Float result = instance.getRandom(max);
            assertTrue(result <= 6.0F);
            assertTrue(result >= 1.0F);
        }
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of eval method, of class EvalPostfix.
     */
//    @Test
//    public void testEval_PostfixModel() throws Exception {
//        System.out.println("eval");
//        PostfixModel model = null;
//        EvalPostfix instance = new EvalPostfix();
//        String expResult = "";
//        String result = instance.eval(model);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
