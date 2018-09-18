package com.loganalyzer.service;

import com.loganalyzer.model.SearchResult;
import com.loganalyzer.repository.SearchResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchResultServiceImpl implements SearchResultService {

    @Autowired
    private SearchResultRepository searchResultRepository;

    @Override
    public SearchResult findByQuery(String query) {
        return searchResultRepository.findByQuery(query);
    }
}
