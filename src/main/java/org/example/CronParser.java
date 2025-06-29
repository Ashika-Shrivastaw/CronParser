package org.example;

import java.util.Scanner;

public class CronParser {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the cron expression : ");
        String inputString = sc.nextLine().trim();

        if(inputString.isEmpty()){
            System.out.println("No input provided. Exiting. ");
            sc.close();
            return;
        }
        GenerateCronOutput.generateOutput(inputString);
        sc.close();
    }
}
