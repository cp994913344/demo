package com.ljs.demo.Service.Impl;

import com.ljs.demo.Service.FileService;
import com.ljs.demo.pojo.domain.MFile;
import com.ljs.demo.pojo.mapper.FileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;

    @Override
    public MFile selectByPrimaryKey(Integer id) {
        return fileMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(MFile file) {
        return fileMapper.insert(file);
    }
}
