package com.loganalyzer.controller;

import com.loganalyzer.repository.SearchResultRepository;
import com.loganalyzer.repository.SearchResultRepositoryImpl;
import com.loganalyzer.service.SearchResultService;
import com.loganalyzer.service.SearchResultServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchResultController.class)
@TestPropertySource(properties = {"log.path = ./src/test/java/com/loganalyzer/repository/testData/error.log"})
public class SearchResultControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public SearchResultService searchResultService() {
            return new SearchResultServiceImpl();
        }

        @Bean
        public SearchResultRepository searchResultRepository() {
            return new SearchResultRepositoryImpl();
        }
    }

    @Test
    public void whenSearchForUniqueQuery_thenReturnOneResult() throws Exception {

        String q = "timed out";
        mvc.perform(get("/api/search?q=" + q)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(q)))
                .andExpect(jsonPath("$.results", hasSize(1)));
    }

    @Test
    public void whenSearchWithRepeatedQuery_thenReturnMultipleResults() throws Exception {

        String q = "error";
        mvc.perform(get("/api/search?q=" + q)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(q)))
                .andExpect(jsonPath("$.results", hasSize(5)));
    }

    @Test
    public void whenSearchWithEmptyQuery_thenReturnAllLines() throws Exception {

        String q = "";
        mvc.perform(get("/api/search?q=" + q)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results", hasSize(215)));
    }
}