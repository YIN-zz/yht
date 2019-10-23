package com.ygt.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Abnormal {

    //异常主键id
    private Integer aid;
    //运输备案证号
    private String transport;
    //发生异常情况
    private String ainfo;
    //发生异常时间
    private String atime;
    //发生异常地址
    private String aaddress;
    //发生异常备注
    private String acomment;
    //绑定手机号
    private String dphone;
}
