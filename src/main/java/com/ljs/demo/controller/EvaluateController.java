package com.ljs.demo.controller;

import com.ljs.demo.Service.EvaluateService;
import com.ljs.demo.common.constant.GetUuid;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.Evaluate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/evaluate")
public class EvaluateController {

    @Autowired
    EvaluateService evaluateService;

    @RequestMapping(value = "/insert")
    public ResponseMessage insert(){
        Evaluate evaluate = new Evaluate();
        evaluate.setUuid(GetUuid.uuid);
        evaluate.setEvaluateid(1);
        evaluate.setDescription("1");
        evaluate.setFace("1");
        evaluate.setMajor("1");
        evaluate.setManner("1");
        evaluate.setRemark("ljs");
        int i = evaluateService.insert(evaluate);
        log.info("|对外接口|返回参数[{}]", i);
        return ResponseMessage.ok("",i);
    }
}
