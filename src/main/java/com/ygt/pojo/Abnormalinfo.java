package com.ygt.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Abnormalinfo {
    //异常主键id
    private Integer abnormalid;
    //发生异常情况
    private String abnormalstatus;
    //发生异常备注（详细情况）
    private String abnormalmessage;
    //发生异常时间
    private String abnormaltime;
    //发生异常地址
    private String abnormaladdress;
    //运输信息的id
    private Integer transportid;
}
