package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.ServiceInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceInfoService {

    ServiceInfo selectByPrimaryKey(Integer id);

    List<String> queryByTourUid(String tourUid);
}
