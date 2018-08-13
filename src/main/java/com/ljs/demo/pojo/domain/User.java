package com.ljs.demo.pojo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -6970675031171558256L;

    private String id;
    private String name;
}
