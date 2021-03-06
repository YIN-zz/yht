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
    //货物类型
    private String goodtype;
    //货物的用途
    private String goodruse;
    //货物名称
    private String goodname;
    //货物数量
    private Integer goodcount;
    //货物重量
    private Double goodweight;
    //出入库id
    private Integer driverid;
/*    //库内货物总id
    private Integer chid;*/

}
