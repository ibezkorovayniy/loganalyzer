package com.myloganalyzer.loganalyzer.repository;

import com.myloganalyzer.loganalyzer.model.Line;
import com.myloganalyzer.loganalyzer.model.Result;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResultRepositoryImpl implements ResultRepository {

    private File file = new File("/Users/illia/Documents/IdeaProjets/loganalyzer/simple/error_log");
    private Result result = new Result();

    @Override
    public Result findByParam(String param) {
        List<Line> lines = new ArrayList<>();
        int lineCount = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempStr;
            while((tempStr = reader.readLine()) != null) {
                lineCount++;
                if(tempStr.contains(param)) {
                    lines.add(new Line(lineCount, tempStr));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
        result.setResults(lines);
        result.setCount(lines.size());
        return result;
    }
}
