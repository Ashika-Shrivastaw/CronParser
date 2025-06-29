package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ParseCronExp {
    public static List<Integer> ParseCronInputExpression(String expression,CronStringFields field){

        if(expression == null || expression.isEmpty())
            throw new IllegalArgumentException("Empty expression for the field :" +field.fieldName);
        
        Set<Integer> eachRow = new TreeSet<>();
        for(String eachPart : expression.split("\\s*,\\s*", -1)){
            if(eachPart.contains("/")){
                String subPart[] = eachPart.split("/");
                if (subPart.length != 2) {
                    throw new IllegalArgumentException("Invalid cron syntax: " + eachPart);
                }
                String rangePart = subPart[0];
                int value;
                try{
                    value = Integer.parseInt(subPart[1]);
                } catch (NumberFormatException e){
                    throw new IllegalArgumentException("Invalid value in : " + eachPart);
                }
                if(value<=0)
                    throw new IllegalArgumentException("Value must be a positive number in : " + eachPart);

                int lowerValue = field.fieldMinValue;
                int upperValue = field.fieldMaxValue;
                if(rangePart.contains("-")){
                	String[] eachRangeVal = rangePart.split("-");
                	if (eachRangeVal.length != 2) {
                        throw new IllegalArgumentException("Invalid range in: " + eachPart);
                    }
                    lowerValue=parseAndValidate(eachRangeVal[0], field);
                    upperValue=parseAndValidate(eachRangeVal[1], field);
                    
                } else if(!rangePart.equals("*")){
                    lowerValue=parseAndValidate(rangePart, field);
                } 
                for(int i=lowerValue; i<=upperValue; i=i+value){
                	eachRow.add(i);
	           }
            }
            else if(eachPart.contains("*")){
                int lowerValue = field.fieldMinValue;
                int upperValue = field.fieldMaxValue;
                for(int i=lowerValue;i<=upperValue;i++){
                	eachRow.add(i);
                }
            } else if(eachPart.contains("-")){
                String subpart[]=eachPart.split("-");
                if (subpart.length != 2) {
                    throw new IllegalArgumentException("Invalid range in: " + eachPart);
                }
                //String bound[] = rangePart.split("-");
                int lowerValue=parseAndValidate(subpart[0], field);
                int upperValue=parseAndValidate(subpart[1], field);
                
                if (lowerValue > upperValue) {
                    throw new IllegalArgumentException("Range starting value is greater than ending value in: " + eachPart);
                }
                
                for(int i=lowerValue;i<=upperValue;i++){
                	eachRow.add(i);
                }
            } else {
            	int value = parseAndValidate(eachPart, field);
            	eachRow.add(value);
            }
        }
        return new ArrayList<>(eachRow);
    }
    
    private static int parseAndValidate(String token, CronStringFields field) {
        int value;
        try {
            value = Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: " + token);
        }
        if (value < field.fieldMinValue || value > field.fieldMaxValue) {
            throw new IllegalArgumentException(
                String.format("Value %d out of range for %s (%d-%d)", value, field.fieldName, field.fieldMinValue, field.fieldMaxValue)
            );
        }
        return value;
    }
}
