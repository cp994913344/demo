package com.ljs.demo.Service.Impl;

import com.ljs.demo.Service.ScenicService;
import com.ljs.demo.pojo.domain.Scenic;
import com.ljs.demo.pojo.mapper.ScenicMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ScenicServiceImpl implements ScenicService {

    @Autowired
    ScenicMapper scenicMapper;

    @Override
    public Scenic selectByPrimaryKey(Integer id) {
        return scenicMapper.selectByPrimaryKey(id);
    }

    /**
     *查询热门景点
     */
    @Override
    public List<Scenic> queryHotScenic() {
        return scenicMapper.queryHotScenic();
    }
}
