package com.bp.learnblog.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 尝试测试
 */
@Slf4j
@SpringBootTest
public class TestControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeTestClass
    public void beforeTestClass() {
        System.out.println("BeforeTestClass Tests");
        System.out.println("----------------------------------");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("BeforeAll Tests");
        System.out.println("----------------------------------");
    }

    @BeforeEach
    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("test"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        log.info("test() MockResult: status-" + mvcResult.getResponse().getStatus() +
                ", contest-" + mvcResult.getResponse().getContentAsString());
    }
}
