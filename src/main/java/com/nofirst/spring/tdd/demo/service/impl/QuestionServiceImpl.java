package com.nofirst.spring.tdd.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nofirst.spring.tdd.demo.mbg.mapper.QuestionMapper;
import com.nofirst.spring.tdd.demo.model.vo.QuestionVo;
import com.nofirst.spring.tdd.demo.service.QuestionService;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.QuestionExample;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Question service.
 */
@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private QuestionMapper questionMapper;


    @Override
    public PageInfo<QuestionVo> index(Integer pageIndex, Integer pageSize) {
        QuestionExample example = new QuestionExample();

        PageHelper.startPage(pageIndex, pageSize);
        List<Question> questions = questionMapper.selectByExampleWithBLOBs(example);
        // 如果不使用 mapper 返回的对象直接构造分页对象，total会被错误赋值成当前页的数据的数量，而不是总数
        PageInfo<Question> questionPageInfo = new PageInfo<>(questions);
        List<QuestionVo> result = new ArrayList<>();
        for (Question question : questions) {
            QuestionVo questionVo = new QuestionVo();
            questionVo.setId(question.getId());
            questionVo.setUserId(question.getUserId());
            questionVo.setTitle(question.getTitle());
            questionVo.setContent(question.getContent());
            result.add(questionVo);
        }
        PageInfo<QuestionVo> pageResult = new PageInfo<>();
        pageResult.setTotal(questionPageInfo.getTotal());
        pageResult.setPageNum(questionPageInfo.getPageNum());
        pageResult.setPageSize(questionPageInfo.getPageSize());
        pageResult.setList(result);
        return pageResult;
    }
}
