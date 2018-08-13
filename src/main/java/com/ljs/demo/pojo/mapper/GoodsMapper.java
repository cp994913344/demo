package com.ljs.demo.pojo.mapper;

import org.apache.ibatis.annotations.Param;

public interface GoodsMapper {

    public int updateById(@Param("price") double gdPrice,@Param("id") Integer gdId);
}
