package com.ygt.pojo;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Companyinfo {
    //公司的id
    private Integer companyid;
    //公司的名字
    private String companyname;
    //公司的地址
    private String companyaddres;
    //公司的负责人
    private String companypeople;
    //公司负责人电话
    private String companyphone;
}
