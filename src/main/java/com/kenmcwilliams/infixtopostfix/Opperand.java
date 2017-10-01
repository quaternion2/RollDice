/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.infixtopostfix;

import java.util.List;

/**
 * NOTE: All opperands to be parsed are positive integers
 *
 * @author ken
 */
public class Opperand extends Token {

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        SCALAR, VECTOR
    };

    private Float value;
    private List<Float> values;
    private Type type;

    public Opperand(Float value) {
        //this();
        this.setValue(value);//TODO: Review
    }

    public Opperand(List<Float> values) {
        //this();
        this.setValues(values);//TODO: Review
    }

    Opperand() {
        //super(TokenType.OPPERAND);
    }

    public Float getValue() {
        if (this.getType() == Type.VECTOR) {
            this.toScalar();
        }
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
        this.values = null;
        this.setType(Type.SCALAR);
        this.setSymbol("" + value.intValue());
    }

    /**
     * @return the values
     */
    public List<Float> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(List<Float> values) {
        this.values = values;
        this.value = null;
        this.setType(Type.VECTOR);
    }

    public void toScalar() {
        Float total = 0.0f;
        if (this.getType() == Type.VECTOR) {
            for (Float v : this.values) {
                total += v;
            }
            this.setValue(total);
        }
    }

    @Override
    public String toString() {
        if (this.type == Type.SCALAR) {
            return "" + this.value;
        } else {
            return this.values.toString();
        }
    }
}
