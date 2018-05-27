package com.ljs.demo.Service.Impl;

import com.ljs.demo.Service.JourneyService;
import com.ljs.demo.pojo.domain.Journey;
import com.ljs.demo.pojo.mapper.JourneyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    JourneyMapper journeyMapper;

    @Override
    public Journey selectByPrimaryKey(Integer id) {
        return journeyMapper.selectByPrimaryKey(id);
    }
}
