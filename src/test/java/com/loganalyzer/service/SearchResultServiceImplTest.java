package com.loganalyzer.service;

import com.loganalyzer.model.Line;
import com.loganalyzer.model.SearchResult;
import com.loganalyzer.repository.SearchResultRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class SearchResultServiceImplTest {

    @TestConfiguration
    static class SearchResultServiceImplTestContextConfiguration {

        @Bean
        public SearchResultService searchResultService() {
            return new SearchResultServiceImpl();
        }
    }

    @Autowired
    private SearchResultService searchResultService;

    @MockBean
    private SearchResultRepository resultRepository;

    @Before
    public void setUp() {
        String value = "[Sun Mar  7 16:05:49 2004] [info] [client 64.242.88.10] " +
                "(104)Connection reset by peer: " +
                "client stopped connection before send body completed";
        Line line = new Line(65, value);
        List<Line> lines = new ArrayList<>();
        lines.add(line);
        SearchResult result = new SearchResult(lines, 1);
        Mockito.when(resultRepository.findByQuery("info")).thenReturn(result);
    }

    @Test
    public void whenContainsQuery_thenLineShouldBeFound() {
        String query = "info";
        SearchResult found = searchResultService.findByQuery(query);
        assertTrue(found.getResults().get(0).getValue().contains(query));
    }
}