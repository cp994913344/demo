package com.ljs.demo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljs.demo.Service.ServiceInfoService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.ServiceInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/serviceinfo")
public class ServiceInfoController {

    @Autowired
    ServiceInfoService serviceInfoService;

    /**
     * 根据服务ID查询服务
     * @return
     */
    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(){
        ServiceInfo serviceInfo = serviceInfoService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", serviceInfo);
        return ResponseMessage.ok("",serviceInfo);
    }

    /**
     * 分页查询所有服务
     * @return
     */
    @RequestMapping(value = "/queryAllService")
    public ResponseMessage queryAllService(@RequestParam("pageNum") Integer pageNum,
                                           @RequestParam("pageSize") Integer pageSize){
        if(StringUtils.isBlank(pageNum.toString()) || StringUtils.isBlank(pageSize.toString())){
            return ResponseMessage.error("分页信息不能为空");
        }
        /*int pageNum = 1;
        int pageSize = 2;*/
        PageHelper.startPage(pageNum,pageSize);
        List<ServiceInfo> serviceInfoList = serviceInfoService.queryAllService();
        PageInfo pageInfo = new PageInfo(serviceInfoList);
        Page page = (Page)serviceInfoList;
        return ResponseMessage.pageList("服务分页信息",page,pageInfo);
    }

    /**
     * 根据导游Uid查询导游的服务
     * @return
     */
    @RequestMapping(value = "/queryByTourUid")
    public ResponseMessage queryByTourUid(){
        List<String> list = serviceInfoService.queryByTourUid("aa5294254f604f48bfa4f76d21da5850");
        log.info("|对外接口|返回参数[{}]", list);
        return ResponseMessage.list("",list.size(),list);
    }
}
