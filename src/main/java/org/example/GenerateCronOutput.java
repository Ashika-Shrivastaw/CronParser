package org.example;

import java.util.List;

public class GenerateCronOutput {
    public static void generateOutput(String cronString){
    	if(cronString == null || cronString.trim().isEmpty())
    		throw new IllegalArgumentException("The input string is empty.");
    	
        String fields[] = cronString.split("\\s+",6);
        if(fields.length < 6){
            throw new IllegalArgumentException("Expected 5 time (minute, hour, day of\r\n"
            		+ " month, month, and day of week) fields and a command");
        }
        CronStringFields cronStringFields[] = CronStringFields.values();
        try {
	        for(int i=0; i<cronStringFields.length; i++){
	            List<Integer> cronOutput = ParseCronExp.ParseCronInputExpression(fields[i],cronStringFields[i]);
	            System.out.printf("%-14s  ", cronStringFields[i].fieldName);
	            for(int values : cronOutput){
	                System.out.print(values + " ");
	            }
	            System.out.println();
	        }
	        System.out.printf("%-14s%s%n", "command", fields[5]);
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing cron fields: " + e.getMessage());
       }
    }
}
