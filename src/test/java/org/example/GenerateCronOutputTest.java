package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class GenerateCronOutputTest {
	
	private static String VALID_CRON_STRING = "*/15 0 1,15 * 1-5 /usr/bin/find";
	
	@Test
    void generateOutput_WithNullasInput_ShouldThrowError() {
        assertThrows(IllegalArgumentException.class,
                     ()->GenerateCronOutput.generateOutput(null));
	}
	
	@Test
    void generateOutput_EmptyString_ShouldThrowError() {
        assertThrows(IllegalArgumentException.class,
                     ()->GenerateCronOutput.generateOutput(" "));
	}
	
	@Test
    void generateOutput_FewFieldLengthCheck_ShouldThrowError() {
		String cronStr = "1 2 3 4 5";
		IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, 
				()->GenerateCronOutput.generateOutput(cronStr));
		assertTrue(expected.getMessage().contains("Expected 5 time"),"Should match the expected 5 time field message returned");
	}
	
	@Test
	void generateOutput_WithValidInput_ShouldCallParserForEachField() {
		CronStringFields fields[]=CronStringFields.values();
		String[] parts=VALID_CRON_STRING.split("\\s+", 6);
		
		try (MockedStatic<ParseCronExp> parserMock = mockStatic(ParseCronExp.class)) {
			 parserMock.when(() ->
             ParseCronExp.ParseCronInputExpression(anyString(), any(CronStringFields.class))
         ).thenReturn(Collections.emptyList());
			 
		 assertDoesNotThrow(()-> GenerateCronOutput.generateOutput(VALID_CRON_STRING));
		 
		 for(int i=0; i<fields.length; i++) {
			 int idx = i;
                parserMock.verify(() ->
                    ParseCronExp.ParseCronInputExpression(parts[idx], fields[idx])
                );
		    }
		}
	}
	
	@Test
    void generateOutput_parserThrows_ErrorInternally_IfAnyFieldParsingFails() {
        try (MockedStatic<ParseCronExp> parserMock=mockStatic(ParseCronExp.class)) {
            parserMock.when(() ->
                ParseCronExp.ParseCronInputExpression(anyString(), any(CronStringFields.class))
            ).thenThrow(new IllegalArgumentException("bad field"));

            assertDoesNotThrow(() -> GenerateCronOutput.generateOutput(VALID_CRON_STRING));
        }
    }

}