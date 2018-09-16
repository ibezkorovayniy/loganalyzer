package com.myloganalyzer.loganalyzer.controller;

import com.myloganalyzer.loganalyzer.model.Result;
import com.myloganalyzer.loganalyzer.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping("/search")
    public Result search(@RequestParam String q) {

        return resultService.findByParam(q);
    }
}
