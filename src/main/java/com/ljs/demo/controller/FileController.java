package com.ljs.demo.controller;


import com.ljs.demo.Service.FileService;
import com.ljs.demo.common.constant.GetUuid;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.MFile;
import com.ljs.demo.pojo.domain.Visitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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

    /**
     * Visitor游客头像上传
     *
     * @param file request
     * @return
     */
    @RequestMapping(value = "/uploadPhoto")
    public ResponseMessage uploadPhoto(@RequestParam("file") MultipartFile file , Visitor visitor , HttpServletRequest request) throws IOException {
        log.info("|对外接口|入参[{}]", visitor);
        if(file.isEmpty()){
            return ResponseMessage.error("文件不能为空");
        }
        String path = request.getServletContext().getRealPath("/images/");
        String name = file.getOriginalFilename();

        String filename = path + GetUuid.uuid+name;
        log.info("|文件存储路径|[{}]",filename);
        int i = 0;
        if (visitor.getVisitorid() != null){
            MFile f = new MFile();
            f.setFilename(filename);
            f.setUpdateuserid(visitor.getVisitorid());
            f.setUpdatedate(new Date());
            f.setUserid(visitor.getUuid());
            f.setDeleted(0);

            i = fileService.insert(f);
        }
        file.transferTo(new File(filename));
        if(i == 0){
            return ResponseMessage.error("存储失败");
        }
        return ResponseMessage.ok("存储成功",i);
    }


}
