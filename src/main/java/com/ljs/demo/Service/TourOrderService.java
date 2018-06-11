package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Tourorder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TourOrderService {

    Tourorder selectByPrimaryKey(Integer id);

    List<Tourorder> queryOrderByTourUid(String uuid);
}
