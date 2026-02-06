package com.nofirst.spring.tdd.demo.controller;

import com.nofirst.spring.tdd.demo.BaseContainerTest;
import com.nofirst.spring.tdd.demo.factory.QuestionFactory;
import com.nofirst.spring.tdd.demo.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.QuestionExample;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// 关键：添加TestInstance注解，设置为PER_CLASS模式，允许@BeforeAll使用非static方法
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ViewQuestionsTests extends BaseContainerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private QuestionMapper questionMapper;


    @BeforeAll
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @BeforeEach
    public void setupTestData() {
        QuestionExample example = new QuestionExample();
        // 空条件，匹配所有数据，等价于delete * from question
        example.createCriteria();
        questionMapper.deleteByExample(example);
    }

    @Test
    void user_can_view_questions() throws Exception {
        // given
        Question question = QuestionFactory.createQuestion();
        questionMapper.insert(question);

        // when
        MvcResult result = this.mockMvc.perform(get("/questions?pageIndex=1&pageSize=10"))
                .andExpect(status().isOk()).andReturn();

        String json = result.getResponse().getContentAsString();

        // then
        assertThat(json).contains(question.getTitle());
        assertThat(json).contains(question.getContent());
    }

    @Test
    void user_can_view_a_single_question() throws Exception {
        // given
        Question question = QuestionFactory.createQuestion();
        questionMapper.insert(question);

        // when
        MvcResult result = this.mockMvc.perform(get("/questions/{id}", question.getId()))
                .andExpect(status().isOk()).andReturn();

        String json = result.getResponse().getContentAsString();

        // then
        assertThat(json).contains(question.getTitle());
        assertThat(json).contains(question.getContent());
    }
}
