package com.loganalyzer.controller;

import com.loganalyzer.repository.SearchResultRepository;
import com.loganalyzer.repository.SearchResultRepositoryImpl;
import com.loganalyzer.service.SearchResultService;
import com.loganalyzer.service.SearchResultServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchResultController.class)
@TestPropertySource(properties = {"log.path = ./src/test/java/com/loganalyzer/repository/testData/error.log"})
public class SearchResultControllerTest {

    @Autowired
    private MockMvc mvc;

    @TestConfiguration
    static class SearchResultServiceImplTestContextConfiguration {

        @Bean
        public SearchResultService searchResultService() {
            return new SearchResultServiceImpl();
        }
    }
    @TestConfiguration
    static class SearchResultRepositoryImplTestContextConfiguration {

        @Bean
        public SearchResultRepository searchResultRepository() {
            return new SearchResultRepositoryImpl();
        }
    }

    @Autowired
    @Qualifier("searchResultRepository")
    private SearchResultRepository resultRepository;

    @Autowired
    @Qualifier("searchResultService")
    private SearchResultService resultService;

    @Test
    public void whenSearchWithQueryInfo_thenReturnMatchedStrings() throws Exception {

        String value = "[Sun Mar  7 16:05:49 2004] [info] [client 64.242.88.10] " +
                "(104)Connection reset by peer: " +
                "client stopped connection before send body completed";

        String q = "info";
        mvc.perform(get("/api/search?q=" + q)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(q)))
                .andExpect(content().string(containsString(value)));
    }

    @Test
    public void whenSearchWithQueryError_thenReturnMatchedStrings() throws Exception {

        String q = "error";
        mvc.perform(get("/api/search?q=" + q)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(q)));
    }
}