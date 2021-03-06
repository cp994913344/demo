package com.ljs.demo.pojo.mapper;
import com.ljs.demo.pojo.domain.MyVisitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyVisitorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYVISITOR
     *
     * @mbggenerated Thu May 24 19:49:11 CST 2018
     */
    int deleteByPrimaryKey(Integer myvisitorid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYVISITOR
     *
     * @mbggenerated Thu May 24 19:49:11 CST 2018
     */
    int insert(MyVisitor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYVISITOR
     *
     * @mbggenerated Thu May 24 19:49:11 CST 2018
     */
    int insertSelective(MyVisitor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYVISITOR
     *
     * @mbggenerated Thu May 24 19:49:11 CST 2018
     */
    MyVisitor selectByPrimaryKey(Integer myvisitorid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYVISITOR
     *
     * @mbggenerated Thu May 24 19:49:11 CST 2018
     */
    int updateByPrimaryKeySelective(MyVisitor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MYVISITOR
     *
     * @mbggenerated Thu May 24 19:49:11 CST 2018
     */
    int updateByPrimaryKey(MyVisitor record);


    List<MyVisitor> queryByToUid(@Param("toUid") String toUid);
}