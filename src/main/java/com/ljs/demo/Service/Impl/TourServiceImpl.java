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
    public TourVo selectByPrimaryKey(Integer id) {
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

    @Override
    public List<TourVo> querySelective(Tour tour) {
        return tourMapper.querySelective(tour);
    }

    @Override
    public List<Tour> queryHotTour() {
        return tourMapper.queryHotTour();
    }

    @Override
    public List<Tour> queryTour() {
        return tourMapper.queryTour();
    }

    @Override
    public int deleteTour(Integer id) {
        return tourMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int tourPass(Integer id) {
        return tourMapper.tourPass(id);
    }
}
