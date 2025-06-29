# CronParser Project

A simple command‑line Java application that parses a standard 5‑field cron expression + command, expands each field into its concrete time values, and prints a formatted schedule table. Includes a basic automated test suite.

# Project Overview
1. CronStringFields - Defines cron fields (minute, hour, day of month, month, day of week) and their valid numeric ranges.
2. CronParser - main entry point. Parses arguments, runs tests or prints output.
3. GenerateCronOutput - Contains logic to expand expressions (*, 1-5, */15, 1,15) into sorted lists of integers.
4. ParseCronExp - Formats and prints the expanded values alongside their field names.

CronStringFieldsTest, CronParserTest, GenerateCronOutputTest, ParseCronExpTest - Contains unit test cases for each of them

# Prerequisites
Java Development Kit (JDK) version 8 or higher

Optional: Maven (if you wish to import into an IDE) or simply the command‑line javac/java tools

Supported on Windows, macOS, and Linux.

# Setup & Build
Clone or download this repository to your local machine.

Open a terminal (Command Prompt on Windows, Terminal on macOS/Linux) and navigate to the project directory

Compile all .java files:
This will produce .class files in the same directory.

# Running the Application

Run the CronParser application - It will promt for an input in the terminal.

### Sample input :  "*/15 0 1,15 * 1-5 /usr/bin/find"

> Example output:
> 
> minute        0 15 30 45
> 
> hour          0
> 
> day of month  1 15
> 
> month         1 2 3 … 12
> 
> day of week   1 2 3 4 5
> 
> command       /usr/bin/find
> 

# Running Tests
Run all the test files

You should see: all test cases passing
