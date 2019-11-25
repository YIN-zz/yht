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
    //头像显示
    private String userphoto;
    //注册人身份
    private Integer useridentity;
    //人员是否可见（可见0、不可见1）
    private Integer uservisible;
}
