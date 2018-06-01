package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Tour;
import com.ljs.demo.pojo.vo.TourVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TourService {

    Tour selectByPrimaryKey(Integer id);

    int insertTour(Tour tour);

    List<TourVo> queryByCityUID(String cityinfoid);

    List<TourVo> querySelective(Tour tour);
}
