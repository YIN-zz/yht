package com.ygt.pojo;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Managerinfo {

    private Integer mid;
    //企管手机号
    private String mphone;
    //企管名字
    private String mname;
    //企管密码
    private String mpassword;
    //企业名称
    private String mrelease;
    //企业地址
    private String maddress;
    //企管身份
    private String mposition;

}
