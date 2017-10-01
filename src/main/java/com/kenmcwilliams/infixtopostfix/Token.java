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
public abstract class Token {
    private String symbol;
    //private TokenType tokenType;

    public Token(/*TokenType tokenType*/){
        //this.tokenType = tokenType;
    }
    
    /**
     * @return the tokenType
     */
//    public TokenType getTokenType() {
//        return tokenType;
//    }

    
    
    /**
     * @param tokenType the tokenType to set
     */
//    public void setTokenType(TokenType tokenType) {
//        this.tokenType = tokenType;
//    }
    //enum TokenType{OPERATOR, OPPERAND};
    
    public String getSymbol(){
        return symbol;
    }
    
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
}
