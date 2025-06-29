package org.example;

public enum CronStringFields {
    MINUTE("minute",0,59),
    HOUR("hour",0,23),
    DAY_OF_MONTH("day of the month", 1,31),
    MONTH("month",1, 12),
    DAY_OF_WEEK("day of the week",1,7);

    public final String fieldName;
    public final int fieldMinValue;
    public final int fieldMaxValue;

    CronStringFields(String fieldName, int fieldMinValue, int fieldMaxValue) {
        this.fieldName = fieldName;
        this.fieldMinValue = fieldMinValue;
        this.fieldMaxValue = fieldMaxValue;
    }
}
