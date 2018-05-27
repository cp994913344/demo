package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Journey;
import org.springframework.stereotype.Service;

@Service
public interface JourneyService {

    Journey selectByPrimaryKey(Integer id);
}
