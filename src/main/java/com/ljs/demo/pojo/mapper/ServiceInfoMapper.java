package com.ljs.demo.pojo.mapper;

import com.ljs.demo.pojo.domain.ServiceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface ServiceInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERVICEINFO
     *
     * @mbggenerated Thu May 24 21:21:37 CST 2018
     */
    int deleteByPrimaryKey(Integer serviceid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERVICEINFO
     *
     * @mbggenerated Thu May 24 21:21:37 CST 2018
     */
    int insert(ServiceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERVICEINFO
     *
     * @mbggenerated Thu May 24 21:21:37 CST 2018
     */
    int insertSelective(ServiceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERVICEINFO
     *
     * @mbggenerated Thu May 24 21:21:37 CST 2018
     */
    ServiceInfo selectByPrimaryKey(Integer serviceid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERVICEINFO
     *
     * @mbggenerated Thu May 24 21:21:37 CST 2018
     */
    int updateByPrimaryKeySelective(ServiceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERVICEINFO
     *
     * @mbggenerated Thu May 24 21:21:37 CST 2018
     */
    int updateByPrimaryKey(ServiceInfo record);

    List<ServiceInfo> queryAllService();

    List<String> queryByTourUid(@Param("tourUid") String tourUid);

    List<ServiceInfo> queryByTourUID(@Param("tourUid") String tourUid);
}