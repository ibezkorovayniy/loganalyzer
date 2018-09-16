package com.myloganalyzer.loganalyzer.repository;

import com.myloganalyzer.loganalyzer.model.Result;

public interface ResultRepository {

    Result findByParam(String param);
}
