package com.calculabot.formulas;

import java.util.ArrayList;

public class akselarasi extends BasicFormula {
	
    
    public void initiate(){
        question.add("Massa (dalam kg)");
        question.add("Gaya (dalam newton)");
    }
    
    @Override
    public String operate(ArrayList<String> input){
        double result = Double.parseDouble(input.get(1))/Double.parseDouble(input.get(0));
        return Double.toString(result);
    }
}
