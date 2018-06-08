package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Scenic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScenicService {

    Scenic selectByPrimaryKey(Integer id);

    List<Scenic> queryHotScenic();

    List<Scenic> queryScenic();

    int deleteScenic(Integer id);
}
