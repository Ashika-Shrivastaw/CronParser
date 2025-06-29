package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class ParseCronExpTest {
	
	private final CronStringFields FIELD = CronStringFields.MINUTE;

	 @Test
	    void parserCronThrowsError_WhenNullisPassed() {
	        assertThrows(IllegalArgumentException.class,
	            () -> ParseCronExp.ParseCronInputExpression(null, FIELD),
	            "Null expression passed should throw error");
	    }

	    @Test
	    void parserCronThrowsError_WhenEmptyExpressionisPassed() {
	        assertThrows(IllegalArgumentException.class,
	            () -> ParseCronExp.ParseCronInputExpression("", FIELD),
	            "Empty string passed should throw error");
	    }

	    @Test
	    void parserCronInputList_ifMatches_ReturnTrue() {
	        List<Integer> result = ParseCronExp.ParseCronInputExpression("5", FIELD);
	        assertEquals(List.of(5), result);
	    }

	    @Test
	    void parserCronInputList_ifMatches_ReturnTrue_forFullRange_ofMinuteField() {
	        List<Integer> result = ParseCronExp.ParseCronInputExpression("*", FIELD);
	        assertEquals(60, result.size());
	        assertEquals(0, result.get(0));
	        assertEquals(59, result.get(59));
	    }

	    @Test
	    void parserCronInput_CheckForAnyRange_thenReturnCorrectOutput() {
	        List<Integer> result = ParseCronExp.ParseCronInputExpression("10-12", FIELD);
	        assertEquals(List.of(10, 11, 12), result);
	    }

	    @Test
	    void parserCronInput_ReversedRange_throwsError() {
	        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
	            () -> ParseCronExp.ParseCronInputExpression("20-10", FIELD));
	        assertTrue(ex.getMessage().contains("starting value is greater"),
	            "Should complain about reversed range");
	    }

	    @Test
	    void parserCronInput_InvalidRangeFormat_throwsError() {
	        assertThrows(IllegalArgumentException.class,
	            () -> ParseCronExp.ParseCronInputExpression("1-2-3", FIELD),
	            "Too many hyphens should throw error");
	    }

	    @Test
	    void parserCronInput_ValidString_thenReturnCorrectOutput() {
	        List<Integer> result = ParseCronExp.ParseCronInputExpression("*/15", FIELD);
	        assertEquals(List.of(0, 15, 30, 45), result);
	    }

	    @Test
	    void parserCronInput_ValidCronString_thenReturnCorrectOutput() {
	        List<Integer> result = ParseCronExp.ParseCronInputExpression("10-20/5", FIELD);
	        assertEquals(List.of(10, 15, 20), result);
	    }

	    @Test
	    void parserCronInput_InvalidCronString_throwsError() {
	        assertThrows(IllegalArgumentException.class,
	            () -> ParseCronExp.ParseCronInputExpression("5/", FIELD),
	            "Invalid input should throw error");
	        assertThrows(IllegalArgumentException.class,
	            () -> ParseCronExp.ParseCronInputExpression("/5", FIELD),
	            "Invalid input should throw error");
	        assertThrows(IllegalArgumentException.class,
	            () -> ParseCronExp.ParseCronInputExpression("*/0", FIELD),
	            "Non‐positive input should throw error");
	        assertThrows(IllegalArgumentException.class,
	            () -> ParseCronExp.ParseCronInputExpression("*/abc", FIELD),
	            "Non‐numeric input should throw error");
	    }

	    @Test
	    void parserCronInput_InvalidInputNumber_throwsError() {
	        assertThrows(IllegalArgumentException.class,
	            () -> ParseCronExp.ParseCronInputExpression("abc", FIELD),
	            "Nonnumeric token should throw error");
	    }

	    @Test
	    void parserCronInput_OutOfRangeValue_throwsError() {
	        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
	            () -> ParseCronExp.ParseCronInputExpression("99", FIELD));
	        assertTrue(ex.getMessage().contains("out of range"),
	            "Value above max should throw error");
	    }

	    @Test
	    void parserCronInput_ValidCombinedCronString_thenReturnCorrectResult() {
	        List<Integer> result = ParseCronExp.ParseCronInputExpression("0,5-7,*/30", FIELD);
	        assertEquals(List.of(0, 5, 6, 7, 30), result);
	    }
}
