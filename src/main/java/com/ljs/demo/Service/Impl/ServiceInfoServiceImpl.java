package com.ljs.demo.Service.Impl;

import com.ljs.demo.Service.ServiceInfoService;
import com.ljs.demo.pojo.domain.ServiceInfo;
import com.ljs.demo.pojo.mapper.ServiceInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ServiceInfoServiceImpl implements ServiceInfoService {

    @Autowired
    ServiceInfoMapper serviceInfoMapper;

    @Override
    public ServiceInfo selectByPrimaryKey(Integer id) {
        return serviceInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<String> queryByTourUid(String tourUid) {
        return serviceInfoMapper.queryByTourUid(tourUid);
    }
}
