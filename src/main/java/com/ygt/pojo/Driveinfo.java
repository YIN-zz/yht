package com.ygt.pojo;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Driveinfo {

    private Integer did;
    //司机手机号
    private String dphone;
    //司机姓名
    private String dname;
    //司机设置密码
    private String dpassword;

}
