package com.myloganalyzer.loganalyzer.service;

import com.myloganalyzer.loganalyzer.model.Result;

public interface ResultService {

    Result findByParam(String param);
}
