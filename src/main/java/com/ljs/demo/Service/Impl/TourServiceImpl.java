package com.ljs.demo.Service.Impl;

import com.ljs.demo.Service.TourService;
import com.ljs.demo.pojo.domain.Tour;
import com.ljs.demo.pojo.mapper.TourMapper;
import com.ljs.demo.pojo.vo.TourVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TourServiceImpl implements TourService {

    @Autowired
    TourMapper tourMapper;

    @Override
    public Tour selectByPrimaryKey(Integer id) {
        return tourMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertTour(Tour tour) {
        return tourMapper.insert(tour);
    }

    @Override
    public List<TourVo> queryByCityUID(String cityinfoid) {
        return tourMapper.queryByCityUID(cityinfoid);
    }
}
