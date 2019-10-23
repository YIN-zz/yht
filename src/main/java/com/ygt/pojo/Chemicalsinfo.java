package com.ygt.pojo;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Chemicalsinfo {
    //易制毒材料id
    private Integer chid;
    //公司名称
    private String company;
    //易制毒种类
    private String ctype;
    //易制毒材料名称
    private String cgoods;
    //易制毒材料重量
    private double cwerght;
    //易制毒材料数量
    private int ccount;
}
