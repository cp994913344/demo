package com.ljs.demo.pojo.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -6970675031171558256L;

    private String id;
    private String name;

    public static void main(String[] args) {
        User user = new User();
        user.setName("1");
        System.out.println(user.toString());
        System.out.println(LocalDate.now());
        System.out.println(new Date());
    }
}
