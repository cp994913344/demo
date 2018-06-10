package com.ljs.demo.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyVisitor implements Serializable{

    private static final long serialVersionUID = -6970675031171558256L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYVISITOR.MYVISITORID
     *
     * @mbggenerated Thu May 24 19:49:11 CST 2018
     */
    private Integer myvisitorid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYVISITOR.UUID
     *
     * @mbggenerated Thu May 24 19:49:11 CST 2018
     */
    private String uuid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYVISITOR.WITHNAME
     *
     * @mbggenerated Thu May 24 19:49:11 CST 2018
     */
    private String withname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYVISITOR.WITHPHONE
     *
     * @mbggenerated Thu May 24 19:49:11 CST 2018
     */
    private String withphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYVISITOR.WITHSEX
     *
     * @mbggenerated Thu May 24 19:49:11 CST 2018
     */
    private String withsex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYVISITOR.WITHCITY
     *
     * @mbggenerated Thu May 24 19:49:11 CST 2018
     */
    private String withcity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYVISITOR.WITHPHOTO
     *
     * @mbggenerated Thu May 24 19:49:11 CST 2018
     */
    private String withphoto;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MYVISITOR.WITHPHOTO
     *
     * @mbggenerated Thu May 24 19:49:11 CST 2018
     */
    private String visitorid;

    /**
     * 同游表外键
     */
    private String tovisitorid;

    private Integer status;

}
