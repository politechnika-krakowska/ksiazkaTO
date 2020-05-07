package org.pk.jp.jkkn.rest;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.pk.jp.jkkn.data.CategoryRepository;
import org.pk.jp.jkkn.data.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryResourceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createCategoryTest() throws Exception {
        mockMvc.perform(post("/rest/category").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"test category\"}"))
                .andExpect(status().isCreated());

        categoryRepository.deleteAll();

    }

    @Test
    public void getCategoryTest() throws Exception {

        Category cat = categoryRepository.save(new Category("test_add_1"));

        mockMvc.perform(get("/rest/category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("test_add_1"));

        categoryRepository.deleteAll();

    }

    @Test
    public void deleteCategoryTest() throws Exception {

        Category cat = categoryRepository.save(new Category("test_add_2"));

        mockMvc.perform(delete("/rest/category/" + cat.getId()))
                .andExpect(status().is2xxSuccessful());

        Assert.assertFalse(categoryRepository.findById(cat.getId()).isPresent());

        categoryRepository.deleteAll();

    }

}
