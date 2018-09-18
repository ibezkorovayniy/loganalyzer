package com.loganalyzer.repository;

import com.loganalyzer.model.SearchResult;

public interface SearchResultRepository {

    SearchResult findByQuery(String param);
}
