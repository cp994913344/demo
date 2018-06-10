package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Tour;
import com.ljs.demo.pojo.vo.TourVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TourService {

    TourVo selectByPrimaryKey(Integer id);

    int insertTour(Tour tour);

    List<TourVo> queryByCityUID(String cityinfoid);

    List<TourVo> querySelective(Tour tour);

    List<Tour> queryHotTour();

    List<Tour> queryTour();

    int deleteTour(Integer id);

    int tourPass(Integer id);
}
