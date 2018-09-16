package com.myloganalyzer.loganalyzer.service;

import com.myloganalyzer.loganalyzer.model.Result;
import com.myloganalyzer.loganalyzer.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Result findByParam(String param) {
        return resultRepository.findByParam(param);
    }
}
