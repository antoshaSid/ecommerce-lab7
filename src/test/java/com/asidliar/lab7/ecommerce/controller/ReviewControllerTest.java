package com.asidliar.lab7.ecommerce.controller;

import com.asidliar.lab7.ecommerce.constants.ErrorMessage;
import com.asidliar.lab7.ecommerce.constants.PathConstants;
import com.asidliar.lab7.ecommerce.dto.review.ReviewRequest;
import com.asidliar.lab7.ecommerce.util.TestConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/create-perfumes-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/create-perfumes-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getReviewsByPerfumeId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(PathConstants.API_V1_REVIEW + PathConstants.PERFUME_ID, 2)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[*].id").isNotEmpty())
                .andExpect(jsonPath("$[*].author").isNotEmpty())
                .andExpect(jsonPath("$[*].message").isNotEmpty())
                .andExpect(jsonPath("$[*].rating").isNotEmpty())
                .andExpect(jsonPath("$[*].date").isNotEmpty());
    }

    @Test
    public void addReviewToPerfume() throws Exception {
        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setPerfumeId(1L);
        reviewRequest.setAuthor(TestConstants.FIRST_NAME);
        reviewRequest.setMessage("Hello world");
        reviewRequest.setRating(5);

        mockMvc.perform(MockMvcRequestBuilders.post(PathConstants.API_V1_REVIEW)
                        .content(mapper.writeValueAsString(reviewRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.author", equalTo(TestConstants.FIRST_NAME)))
                .andExpect(jsonPath("$.rating", equalTo(5)))
                .andExpect(jsonPath("$.message", equalTo("Hello world")));
    }

    @Test
    public void addReviewToPerfume_ShouldNotFound() throws Exception {
        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setPerfumeId(111L);
        reviewRequest.setAuthor(TestConstants.FIRST_NAME);
        reviewRequest.setMessage("Hello world");
        reviewRequest.setRating(5);

        mockMvc.perform(MockMvcRequestBuilders.post(PathConstants.API_V1_REVIEW)
                        .content(mapper.writeValueAsString(reviewRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", equalTo(ErrorMessage.PERFUME_NOT_FOUND)));
    }

    @Test
    public void addReviewToPerfume_ShouldInputFieldsAreEmpty() throws Exception {
        ReviewRequest reviewRequest = new ReviewRequest();

        mockMvc.perform(MockMvcRequestBuilders.post(PathConstants.API_V1_REVIEW)
                        .content(mapper.writeValueAsString(reviewRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.authorError", Matchers.is(ErrorMessage.FILL_IN_THE_INPUT_FIELD)))
                .andExpect(jsonPath("$.messageError", Matchers.is(ErrorMessage.FILL_IN_THE_INPUT_FIELD)));
    }
}
