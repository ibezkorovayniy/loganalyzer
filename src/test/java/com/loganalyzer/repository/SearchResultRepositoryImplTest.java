package com.loganalyzer.repository;

import com.loganalyzer.model.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"log.path = ./src/test/java/com/loganalyzer/repository/testData/error.log"})
public class SearchResultRepositoryImplTest {

    @TestConfiguration
    static class SearchResultRepositoryImplTestContextConfiguration {

        @Bean
        public SearchResultRepository searchResultRepository() {
            return new SearchResultRepositoryImpl();
        }
    }

    @Autowired
    private SearchResultRepository searchResultRepository;

    @Test
    public void findByQuery() {
        String query = "error";

        SearchResult result = searchResultRepository.findByQuery(query);

        assertEquals(5, result.getResults().size());
        assertTrue(result.getResults().get(0).getValue().contains("error"));
    }
}