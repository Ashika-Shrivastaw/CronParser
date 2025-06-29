package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CronStringFieldsTest {

	@Test
    void toCheckFieldValuesOrder_andCount() {
        CronStringFields[] vals = CronStringFields.values();
        assertEquals(5, vals.length, "There should be exactly 5 enum constants");
        assertArrayEquals(new CronStringFields[] {
            CronStringFields.MINUTE,
            CronStringFields.HOUR,
            CronStringFields.DAY_OF_MONTH,
            CronStringFields.MONTH,
            CronStringFields.DAY_OF_WEEK
        }, vals, "Enum constants should appear in defined order");
    }

    @Test
    void toCheckMinuteConstant() {
        CronStringFields f = CronStringFields.MINUTE;
        assertEquals("minute", f.fieldName);
        assertEquals(0, f.fieldMinValue);
        assertEquals(59, f.fieldMaxValue);
    }

    @Test
    void toCheckHourConstant() {
        CronStringFields f = CronStringFields.HOUR;
        assertEquals("hour", f.fieldName);
        assertEquals(0, f.fieldMinValue);
        assertEquals(23, f.fieldMaxValue);
    }

    @Test
    void toCheckDayOfMonthConstant() {
        CronStringFields f = CronStringFields.DAY_OF_MONTH;
        assertEquals("day of the month", f.fieldName);
        assertEquals(1, f.fieldMinValue);
        assertEquals(31, f.fieldMaxValue);
    }

    @Test
    void toCheckMonthConstant() {
        CronStringFields f = CronStringFields.MONTH;
        assertEquals("month", f.fieldName);
        assertEquals(1, f.fieldMinValue);
        assertEquals(12, f.fieldMaxValue);
    }

    @Test
    void toCheckDayOfWeekConstant() {
        CronStringFields f = CronStringFields.DAY_OF_WEEK;
        assertEquals("day of the week", f.fieldName);
        assertEquals(1, f.fieldMinValue);
        assertEquals(7, f.fieldMaxValue);
    }

    @Test
    void allValidFields_ShouldReturnCorrectConstant() {
        assertSame(CronStringFields.MINUTE, CronStringFields.valueOf("MINUTE"));
        assertSame(CronStringFields.HOUR, CronStringFields.valueOf("HOUR"));
        assertSame(CronStringFields.DAY_OF_MONTH, CronStringFields.valueOf("DAY_OF_MONTH"));
        assertSame(CronStringFields.MONTH, CronStringFields.valueOf("MONTH"));
        assertSame(CronStringFields.DAY_OF_WEEK, CronStringFields.valueOf("DAY_OF_WEEK"));
    }

    @Test
    void throwsErrorWhen_NonExistingEnumConstant_isCalled() {
        assertThrows(IllegalArgumentException.class,
            () -> CronStringFields.valueOf("YEAR"),
            "Requesting a non-existent enum constant throws error");
    }
}
