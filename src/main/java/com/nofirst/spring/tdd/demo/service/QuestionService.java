package com.nofirst.spring.tdd.demo.service;

import com.github.pagehelper.PageInfo;
import com.nofirst.spring.tdd.demo.model.vo.QuestionVo;

/**
 * QuestionService
 *
 * @author nofirst
 */
public interface QuestionService {


    /**
     * Index page info.
     *
     * @param pageIndex the page index
     * @param pageSize  the page size
     * @return the page info
     */
    PageInfo<QuestionVo> index(Integer pageIndex, Integer pageSize);

    QuestionVo show(Integer id);
}
