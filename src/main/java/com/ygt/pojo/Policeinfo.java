package com.ygt.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Policeinfo {

    //警员主键id
    private Integer pid;
    //警员手机号
    private String pphone;
    //警员名称
    private String pname;
    //警员地址
    private String paddress;
    //警员密码
    private String ppassword;
}
