package com.loganalyzer.repository;

import com.loganalyzer.model.Line;
import com.loganalyzer.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchResultRepositoryImpl implements SearchResultRepository {

    @Autowired
    private Environment env;

    @Override
    public SearchResult findByQuery(String query) {
        String path = env.getProperty("log.path");
        File file = new File(path);
        List<Line> lines = new ArrayList<>();
        int lineCount = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                lineCount++;
                if(currentLine.contains(query)) {
                    lines.add(new Line(lineCount, currentLine));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
        SearchResult searchResult = new SearchResult();
        searchResult.setResults(lines);
        searchResult.setCount(lines.size());
        return searchResult;
    }
}
