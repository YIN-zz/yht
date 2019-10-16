package com.ygt.pojo;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transportinfo{
    //主键id
    private Integer tid;
    //运输备案号
    private String transport;
    //司机身份证号
    private String tcard;
    //货物运输的目的地
    private String tbourn;
    //货物预计到达时间
    private Date ttime;
    //货物是否开始运输
    private String tstart;
    //货物是否已经送到
    private String tend;
    //绑定司机手机号
    private String dphone;

}
