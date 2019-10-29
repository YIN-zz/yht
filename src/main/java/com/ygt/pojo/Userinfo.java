package com.ygt.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Userinfo {
    //主键id
    private Integer userid;
    //注册人姓名
    private String username;
    //注册手机号
    private String userphone;
    //注册密码
    private String userpassword;
    //注册人身份
    private Integer useridentity;
}
