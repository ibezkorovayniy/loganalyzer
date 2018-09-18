package com.loganalyzer.model;

import java.util.List;

public class SearchResult {

    private List<Line> results;

    private int count;

    public SearchResult() {
    }

    public SearchResult(List<Line> results, int count) {
        this.results = results;
        this.count = count;
    }

    public List<Line> getResults() {
        return results;
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
