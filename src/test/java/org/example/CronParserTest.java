package org.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class CronParserTest {
	
	public final InputStream originalInput = System.in;
	public final PrintStream originalOutput = System.out;
	
	private ByteArrayOutputStream output;
	
	@BeforeEach
	void setupStreams() {
		output=new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
	}
	
	@AfterEach
	void restoreStreams() {
		System.setIn(originalInput);
		System.setOut(originalOutput);
	}
	
	@Test
	void whenNoInputProvided_thenPrintExitMessageAndDoesNotCallGenerate() {
		System.setIn(new ByteArrayInputStream("\n".getBytes()));
		CronParser.main(new String[0]);
		
		String expectedOutput = output.toString();
		assertTrue(expectedOutput.contains("Enter the cron expression : "), "Should promt for input");
		assertTrue(expectedOutput.contains("No input provided. Exiting. "), "Should print exit message for empty input");
	}
	
	@Test
	void whenValidInputProvided_thenCallsGenerateOutput() {
		String cronExpr = "*/15 0 1,15 * 1-5 /usr/bin/find";
		System.setIn(new ByteArrayInputStream((cronExpr + "\n").getBytes()));
		
		try(MockedStatic<GenerateCronOutput> mockClass = mockStatic(GenerateCronOutput.class)){
			CronParser.main(new String [0]);
			mockClass.verify(() -> GenerateCronOutput.generateOutput(cronExpr));
		}
	}

}
