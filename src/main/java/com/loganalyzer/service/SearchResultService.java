package com.loganalyzer.service;

import com.loganalyzer.model.SearchResult;

public interface SearchResultService {

    SearchResult findByQuery(String query);
}
