package com.app.shop.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public abstract class ControllerTest {

    protected static final String QUERY_PAGE = "page";
    protected static final String QUERY_PAGE_SIZE = "pageSize";

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc chrome;

    protected String asJson(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    protected void tapError(MvcResult mvcResult) throws UnsupportedEncodingException {
        if (mvcResult.getResponse().getStatus() >= 400) {
            System.out.println(mvcResult.getResponse().getContentAsString());
        }
    }
}
