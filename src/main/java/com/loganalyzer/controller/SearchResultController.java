package com.loganalyzer.controller;

import com.loganalyzer.model.SearchResult;
import com.loganalyzer.service.SearchResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SearchResultController {

    @Autowired
    private SearchResultService searchResultService;

    @GetMapping("/search")
    public SearchResult search(@RequestParam String q) {

        return searchResultService.findByQuery(q);
    }
}
