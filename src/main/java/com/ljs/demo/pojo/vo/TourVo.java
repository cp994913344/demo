package com.ljs.demo.pojo.vo;

import com.ljs.demo.pojo.domain.Tour;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TourVo extends Tour {

    private List ServiceList;
}
