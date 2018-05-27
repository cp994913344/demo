package com.ljs.demo.controller;


import com.ljs.demo.Service.FileService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping("/selectById")
    public ResponseMessage insert(){
        File file = fileService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", file);
        return ResponseMessage.ok("文件",file);
    }
}
