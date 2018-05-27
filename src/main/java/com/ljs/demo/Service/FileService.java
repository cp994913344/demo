package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.File;
import org.springframework.stereotype.Service;

@Service
public interface FileService {

    File selectByPrimaryKey(Integer id);
}
