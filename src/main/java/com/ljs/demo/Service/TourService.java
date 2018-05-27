package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Tour;
import org.springframework.stereotype.Service;

@Service
public interface TourService {

    Tour selectByPrimaryKey(Integer id);
}
