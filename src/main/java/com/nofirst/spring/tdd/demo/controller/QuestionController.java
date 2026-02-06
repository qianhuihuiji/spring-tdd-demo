package com.nofirst.spring.tdd.demo.controller;

import com.github.pagehelper.PageInfo;
import com.nofirst.spring.tdd.demo.common.CommonResult;
import com.nofirst.spring.tdd.demo.model.vo.QuestionVo;
import com.nofirst.spring.tdd.demo.service.QuestionService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * QuestionController
 *
 * @author nofirst
 * @date 2020 -08-24 22:24
 */
@RestController
@Validated
@AllArgsConstructor
public class QuestionController {

    private QuestionService questionService;

    /**
     * Index common result.
     *
     * @param pageIndex the page index
     * @param pageSize  the page size
     * @return the common result
     */
    @GetMapping("/questions")
    public CommonResult<PageInfo<QuestionVo>> index(@RequestParam @NotNull Integer pageIndex,
                                                    @RequestParam @NotNull Integer pageSize) {
        PageInfo<QuestionVo> questionPage = questionService.index(pageIndex, pageSize);
        return CommonResult.success(questionPage);
    }

    /**
     * Show question vo.
     *
     * @param id the id
     * @return the question vo
     */
    @GetMapping("/questions/{id}")
    public QuestionVo show(@PathVariable Integer id) {
        return questionService.show(id);
    }
}
