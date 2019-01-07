package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.MFile;
import org.springframework.stereotype.Service;

@Service
public interface FileService {

    MFile selectByPrimaryKey(Integer id);

    int insert(MFile file);
}
