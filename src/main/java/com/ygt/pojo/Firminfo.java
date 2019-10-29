package com.ygt.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Firminfo {
    //主键id
    private Integer firmid;
    //企业名字
    private String  firmname;
    //企业地址
    private String firmaddress;
    //企业负责人名字
    private String firmprincipal;
    //企业电话
    private String firmphone;
    //外键userid
    private Integer userid;
}
