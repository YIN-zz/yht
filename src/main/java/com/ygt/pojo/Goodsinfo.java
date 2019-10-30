package com.ygt.pojo;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Goodsinfo {
    //货物主键id
    private Integer goodid;
    //货物名称
    private String goodname;
    //货物数量
    private Integer goodcount;
    //货物重量
    private Double goodeweight;
    //出入库id
    private Integer driverrid;
    //库内货物总id
    private Integer chid;

}
