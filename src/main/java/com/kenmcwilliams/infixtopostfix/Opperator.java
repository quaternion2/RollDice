/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.infixtopostfix;

/**
 *
 * @author ken
 */
public class Opperator extends Token{

    public int precidence = 0;
    //public char symbol;
    
//    public void setSymbol(char symbol) throws Exception{
//        Opperator opperator = Opperator.isOpperator(symbol);//Will throw an exception if not an opperator
//        this.symbol = symbol;
//        this.precidence = opperator.precidence;
//    }
    public Opperator(String symbol) throws MalformedExpression{
        //super(TokenType.OPERATOR);
        this.isOpperator(symbol.charAt(0));
    }
    
    public Opperator(char symbol) throws MalformedExpression{
        this(""+symbol);
    }
    
    public enum OPP_PRODUCES {
        SCALAR, VECTOR
    }; //NOTE: Probably not needed? 

    public final Boolean isOpperator(char symbol) throws MalformedExpression {
        //Opperator opp = new Opperator();
        this.setSymbol(""+symbol);//convert char to string
        //opp.symbol = symbol;
        switch (symbol) {
            case '(':
                this.precidence = 100; //higher than any real opperator so it won't cause a pop.
                break;
            case '+':
            case '-':
                this.precidence = 0;
                break;
            case '*':
            case '/':
                this.precidence = 1;
                break;
            case 'l'://strip Lowest from opperand (which holds a list)
            case 'h'://strip Highest from opperand (which holds a list)
                this.precidence = 2;
                break;
            case 'd'://dice rolling sum opperator 3d6 generate 3 numbers in the range of 1-6
                this.precidence = 3;
                break;
            default:
                throw new MalformedExpression();//TODO: Change to unidentifed token
        }
        return true;
    }
    
    @Override
    public String toString(){
        return this.getSymbol();
    }

}
