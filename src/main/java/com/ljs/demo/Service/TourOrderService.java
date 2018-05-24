package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Tourorder;
import org.springframework.stereotype.Service;

@Service
public interface TourOrderService {

    Tourorder selectByPrimaryKey(Integer id);
}
