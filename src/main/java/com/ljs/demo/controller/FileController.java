package com.ljs.demo.controller;


import com.ljs.demo.Service.FileService;
import com.ljs.demo.common.constant.GetUuid;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.MFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    /**
     * 根据ID查询
     *
     * @return
     */
    @RequestMapping("/selectById")
    public ResponseMessage selectById(){
        MFile file = fileService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", file);
        return ResponseMessage.ok("文件",file);
    }

    /**
     * 图片插入
     *
     * @return
     */
    @RequestMapping("/insert")
    public ResponseMessage insertFile(){
        MFile file = new MFile();
        file.setFilename("zll");
        file.setFilepath("sdads");
        file.setUserid(GetUuid.uuid);
        file.setUpdatedate(new Date());
        file.setUpdateuserid(1);
        int i = fileService.insert(file);
        log.info("插入图片接口入参|[{}]",file);
        return ResponseMessage.ok("插入成功",i);
    }


}
