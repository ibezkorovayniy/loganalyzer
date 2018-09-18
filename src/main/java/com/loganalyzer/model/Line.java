package com.loganalyzer.model;

public class Line {

    private int lineNumber;
    private String value;

    public Line() {
    }

    public Line(int lineNumber, String value) {
        this.lineNumber = lineNumber;
        this.value = value;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Line{" +
                "lineNumber=" + lineNumber +
                ", value='" + value + '\'' +
                '}';
    }
}
