package com.github.dreamroute.common.util.test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

@AutoConfigureMockMvc
public abstract class MvcTest {

    @Resource
    protected MockMvc mvc;

}
