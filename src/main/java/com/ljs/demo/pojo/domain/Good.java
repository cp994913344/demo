package com.ljs.demo.pojo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "good")
public class Good implements Serializable{

    private static final long serialVersionUID = 5183867821338277104L;

    @Id
    @Column(name = "gd_id")
    @GeneratedValue
    private Integer gdId;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "gd_title")
    private String gdTitle;

    @Column(name = "gd_price")
    private double gdPrice;

}
