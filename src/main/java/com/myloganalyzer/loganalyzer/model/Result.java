package com.myloganalyzer.loganalyzer.model;

import java.util.List;

public class Result {

    private List<Line> results;

    private int count;

    public Result() {
    }

    public List<Line> getResults() {
        return results;
    }

    public void addLine(Line line) {
        results.add(line);
    }

    public void setResults(List<Line> results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
