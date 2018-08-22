package com.ljs.demo.Repository;

import com.ljs.demo.pojo.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodJPA extends JpaRepository<Good,Integer> {

}
