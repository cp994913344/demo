package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.ServiceInfo;
import org.springframework.stereotype.Service;

@Service
public interface ServiceInfoService {

    ServiceInfo selectByPrimaryKey(Integer id);
}
