/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.infixtopostfix;

//import java.util.Scanner;
//import java.util.logging.Level;
//import java.util.logging.Logger;


/**
 * TODO: Add help or usage documentation
 * @author ken
 */
public class Main {

//    InfixToPostfix ifToPf = new InfixToPostfix();
//    EvalPostfix eval = new EvalPostfix();
    StringExecuter executer = new StringExecuter();

    public static void main(String[] args) {
        Main main = new Main();
        main.run(args[0]);
    }

    //TODO: 
    //1) Catch errors and contine
    //2) Add command buffer so up/down arrows will cycle though buffer
    public void run(String input) {
        //while (true) {
        //Scanner reader = new Scanner(System.in);  // Reading from System.in
        //System.out.print("Enter: ");
        //String input = reader.nextLine(); // Scans the next token of the input as an int.
        try {
            executer.execute(input);
        } catch (Exception ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            //Logger.getLogger(input)
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
