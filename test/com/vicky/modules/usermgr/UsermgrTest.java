/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.usermgr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Vicky
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml", "file:web/WEB-INF/dispatcher-servlet.xml"})
public class UsermgrTest {

    public UsermgrTest() {
    }

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform((post("/user/prepareRegister")
                .param("username", "程景")
                .param("password", "5201314liweiqi")
                .param("email", "1181122598@qq.com")
                .param("condition_GT_D_birthday", "1994-10-10")
                .param("pageSize", "10")
                .param("pageIndex", "1")
                .param("order_property", "age")
                .param("order_type", "asc")))
                .andExpect(status().isOk()).andDo(print());
    }

}
