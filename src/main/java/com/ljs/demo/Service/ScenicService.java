package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Scenic;
import org.springframework.stereotype.Service;

@Service
public interface ScenicService {

    Scenic selectByPrimaryKey(Integer id);
}
